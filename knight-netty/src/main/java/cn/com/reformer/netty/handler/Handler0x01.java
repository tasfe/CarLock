package cn.com.reformer.netty.handler;

import cn.com.reformer.netty.adapter.TCPMessageHandlerAdapter;
import cn.com.reformer.netty.bean.BaseParam;
import cn.com.reformer.netty.bean.TcpUser;
import cn.com.reformer.netty.communication.QrcodeTcpMessageSender;
import cn.com.reformer.netty.msg.MSG_0x01;
import cn.com.reformer.netty.util.msg.ClientManager;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  Copyright 2017 the original author or authors hangzhou Reformer 
 * @Description: 心跳处理类
 * @author zhangjin
 * @create 2017-05-08
**/
public class Handler0x01 extends TCPMessageHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(Handler0x01.class);

    @Autowired(required = true)
    private ClientManager clientManager;
    @Autowired(required = true)
    private QrcodeTcpMessageSender qrcodeTcpMessageSender;


    public void doHandle(BaseParam m, ChannelHandlerContext ctx) {

        MSG_0x01 ret=new MSG_0x01();
        ret.setSn(m.getSn());
        ret.setVersion(m.getVersion());
        ret.setNonce(m.getNonce());
        ret.setSign(m.getSign());
        ctx.writeAndFlush(ret);


    }

    private TcpUser getTcpUserByMac(String mac) {
        return new TcpUser();
    }


    public ClientManager getClientManager() {
        return clientManager;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }


}