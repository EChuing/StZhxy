package com.fangzhizun.cowxgzh.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fangzhizun.cowxgzh.datasource.CheckoutDataSource;
import com.fangzhizun.cowxgzh.datasource.MyDataSource;
import com.fangzhizun.cowxgzh.datasource.MyDataSourceMapper;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourUserDevice;
import com.fangzhizun.cowxgzh.po.sys.SysUsers;
import com.fangzhizun.cowxgzh.service.sys.SysUsersService;
import com.fangzhizun.cowxgzh.util.HttpUtil;
import com.fangzhizun.cowxgzh.util.MySqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RenterLoginController {

    @Autowired
    private SysUsersService sysUsersService;

    @RequestMapping(value="/renterLogin")//renterLogin  index
    public String renterLogin(HttpServletRequest request, Model model, String state, String code) throws Exception{
        HttpSession session = request.getSession();

        //初始化变量
        String appId="";//微信appid
        String wxKey = "";//微信秘钥
        String secret = "";//微信支付key
        String wxMerchantNumber ="";//支付商户号
        Integer coId =null;//公司ID
        String companyName ="";//公司名中文全称
        String logoCompany ="";//公司Logo

        try(SqlSession sqlSession = MySqlSessionFactory.newSqlSessionFactory().openSession()){
            MyDataSourceMapper mapper = sqlSession.getMapper(MyDataSourceMapper.class);
            MyDataSource dataSource = mapper.getDataSource(state);

            if(dataSource != null && dataSource.getState() != 0) {
                appId = dataSource.getAppId();
                wxKey = dataSource.getWxKey();
                secret = dataSource.getSecret();
                wxMerchantNumber = dataSource.getWxMerchantNumber();
                coId = dataSource.getId();
                companyName = dataSource.getCompanyName();
                logoCompany = dataSource.getLogoCompany() != null ?
                        dataSource.getLogoCompany() : "http://pic-static.fangzhizun.com/images/logo/fzz_login.png";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        session.setAttribute("logoCompany", logoCompany);//session接收logoCompany
        model.addAttribute("logoCompany",logoCompany);//前台接收接收logoCompany

        Map<String, String> payMap = new HashMap<String, String>();
        payMap.put("appId", appId);//微信appid
        payMap.put("secret", secret);//微信秘钥
        payMap.put("wxKey", wxKey);//微信支付key
        payMap.put("companyName", companyName);//公司名中文全称
        payMap.put("wxMerchantNumber", wxMerchantNumber);//支付商户号
        payMap.put("coId", coId+"");//公司ID
        session.setAttribute("mypay", payMap);
        session.setAttribute("coId",coId);

        if (state != null) {
            String companyNameStr = (String) session.getAttribute("companyName");//在session获取公司名称简称
            if (companyNameStr == null || !state.equals(companyNameStr)) {
                session.setAttribute("companyName", state);
            }
        }
        else {
            return "renterLogin.html";
        }
        String openid = null;
        try {
            // 通过code获取openId
            HttpUtil httpUtil = new HttpUtil();
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
            // 把appid,secret,code 顶替成自己的内容
            String requestUrl = url.replace("APPID", appId).replace("SECRET", secret).replace("CODE", code);
            // 访问网址取得String
            String request1 = httpUtil.getUrl(requestUrl);
            JSONObject jsonDate = JSONObject.parseObject(request1);
            openid = jsonDate.getString("openid");
        } catch (Exception e) {
            System.out.println("获取openid出错");
            e.printStackTrace();
        }
        session.setAttribute("openid", openid);
        //用openid查用户
        SysUsers renter = sysUsersService.getSysUsersPop(openid);//查用户
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(renter);
        if(renter == null){
            return "renterLogin.html";
        }
        session.setAttribute("rentersession", JSON.toJSONString(jsonArray));
        System.out.println("用户IDUserCoding："+renter.getUserCoding());
        session.setAttribute("userCoding",String.valueOf(renter.getUserCoding()));//用户Id
        return "renterIndex.html";//免登录
    }

    //用账号,密码检验登录
    @RequestMapping(value="/user/login")
    @ResponseBody
    public Result<JSONArray> login(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        try {
            if (!("").equals(request.getParameter("company")) && request.getParameter("company")!=null){
                session.setAttribute("companyName",request.getParameter("company"));
            }
            SysUsers sys=new SysUsers();
            sys.setSuName(request.getParameter("userName"));
            sys.setSuPassword(request.getParameter("password"));
            Result<JSONArray> result= sysUsersService.checkUserLoginByNamePssWord(sys);

            if(result.getCode()!=0){
                System.out.println(result.getBody());
                session.setAttribute("rentersession", JSON.toJSONString(result.getBody()));
                session.setAttribute("userCoding",result.getBody().getJSONObject(0).getString("userCoding"));//用户Id
            }
            //将更新openid信息
            String openid = (String) session.getAttribute("openid");
            System.out.println("openid数据："+openid);
            if (null!=openid){
                SysUsers su=new SysUsers();
                su.setUserCoding(Integer.parseInt(result.getBody().getJSONObject(0).getString("userCoding")));
                su.setOpenid(openid);
                sysUsersService.updateRenterOpenId(su);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(-2,"网络异常",null);
        }
    }
}


