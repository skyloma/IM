package com.saifan.action;

import com.alibaba.fastjson.JSON;
import com.saifan.model.EMF;
import com.saifan.model.TokenResult;
import com.saifan.model.Userinfo;
import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Created by ma on 2015/9/8.
 */


@ServerEndpoint(value = "/api")
public class WS {


    private static Logger logger = LogManager.getLogger(WS.class);
    private static final Set<Session> connections = new CopyOnWriteArraySet<Session>();


    String key = "3argexb6r9qie";
    String secret = "Lxh4PraAYI";


    /**
     *
     */

    private EntityManagerFactory emf;


    public WS() {
        emf = EMF.get();
    }


    @OnOpen
    public void onOpen(Session session) {


        logger.info("打开连接" + session.toString());
        connections.add(session);

    }

    @OnMessage

    public void onMessage(Session session, byte[] message) {





    }
    @OnMessage
    public void onMessage(Session session, String message) {
        logger.info("发来消息" + session.toString() + "" + message);
        try {
            if (!message.isEmpty()) {

                Message m = JSON.parseObject(message, Message.class);

                switch (m.getType()) {
                    //取我的token
                    case 1:

                        String token = getToken(m.getContext());

                        try {
                            m.setContext(token);
                            session.getBasicRemote().sendText(JSON.toJSONString(m));

                            logger.info("发送token" + session.toString() + "" + token);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                    //得到小区的用户信息
                    case 2:
                        String users = getUserinfos();
                        try {

                            m.setContext(users);
                            session.getBasicRemote().sendText(JSON.toJSONString(m));
                            logger.info("发送用户" + session.toString() + "" + users);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                    //得到我的朋友
                    case 3:
                        String firends = getFrinds();
                        try {

                            m.setContext(firends);
                            session.getBasicRemote().sendText(JSON.toJSONString(m));
                            logger.info("发送朋友" + session.toString() + "" + firends);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                    default:
                        break;
                }


            }


        } catch (Exception e) {

            logger.info("消息出错" + e.toString());
            e.printStackTrace();
        }


    }

    private String getFrinds() {
        return null;
    }

    //实际项目中要加 where过滤
    private String getUserinfos() {

        EntityManager entityManager = emf.createEntityManager();

        List<Userinfo> userinfos = entityManager.createQuery("select  u from  Userinfo  u").getResultList();

        entityManager.close();

        return JSON.toJSONString(userinfos);
    }

    private String getToken(String context) {
        Userinfo userinfo = JSON.parseObject(context, Userinfo.class);
        String token = null;
        if (userinfo != null) {
            //
            if (userinfo.getUserid() != null && !userinfo.getUserid().equals("")) {

                //从本地 get token
                token = getTokenFromDB(userinfo.getUserid());
                if (token == null) {
                    //从融云api get token
                    token = getTokenFromRongrun(userinfo.getUserid(), userinfo.getName(), userinfo.getPortraituri());
                    if (token != null) {

                        EntityManager entityManager = emf.createEntityManager();
                        userinfo.setToken(token);
                        entityManager.getTransaction().begin();
                        entityManager.merge(userinfo);
                        entityManager.getTransaction().commit();
                        entityManager.close();
                    }
                }

            }
        }
        return token;
    }

    private String getTokenFromRongrun(String userid, String name, String portraituri) {
        SdkHttpResult result = null;
        String token = null;

        try {
            result = ApiHttpClient.getToken(key, secret, userid, name, portraituri, FormatType.json);
            TokenResult tokenResult = JSON.parseObject(result.getResult(), TokenResult.class);
            if (tokenResult.getCode() == 200) {
                token = tokenResult.getToken();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return token;
        }

    }

    private String getTokenFromDB(String userid) {

        Userinfo userinfo = null;
        try {
            userinfo = (Userinfo) emf.createEntityManager().createQuery("select u  from  Userinfo  u where  u.userid=?1").setParameter(1, userid).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userinfo != null)
            return userinfo.getToken();
        else
            return null;
    }

    @OnClose
    public void onClose( Session session) {

        connections.remove(session);

        logger.info("连接关了");
    }


    @OnError
    public  void  onError( Throwable throwable){
        logger.debug("出错了"+throwable.toString() );
        throwable.printStackTrace();

    }

}
