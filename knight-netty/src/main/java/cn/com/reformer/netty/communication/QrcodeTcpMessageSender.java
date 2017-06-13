package cn.com.reformer.netty.communication;

import cn.com.reformer.netty.bean.BaseParam;
import cn.com.reformer.netty.bean.Client;
import cn.com.reformer.netty.util.SignUtils;
import cn.com.reformer.netty.util.msg.ClientManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;


/**
 *  Copyright 2017 the original author or authors hangzhou Reformer 
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @author zhangjin
 * @create 2017-05-08
**/
public class QrcodeTcpMessageSender extends TCPMessageSender {

    @Autowired(required = true)
    private ClientManager clientManager;

    public QrcodeTcpMessageSender(Channel channel) {
        super(channel);
    }

    public QrcodeTcpMessageSender() {
        super();
    }

    public void openDoor(String sn) {

        Client client = ClientManager.getClientBySN(sn);

        ChannelHandlerContext channel = client.getChannel();

        BaseParam baseParam=new BaseParam();
        baseParam.setSn(sn);
        int randomDig=nextInt(10000,100000);
        baseParam.setNonce(String.valueOf(randomDig));
        byte cmd=0x02;
        baseParam.setSign(SignUtils.getSigin(sn,cmd,String.valueOf(randomDig)));

        channel.writeAndFlush(baseParam);
        channel.writeAndFlush(baseParam);


    }

    public int nextInt(final int min, final int max) {

        Random rand = new Random();
        int tmp = Math.abs(rand.nextInt());

        return tmp % (max - min + 1) + min;

    }

    public ClientManager getClientManager() {
        return clientManager;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
}