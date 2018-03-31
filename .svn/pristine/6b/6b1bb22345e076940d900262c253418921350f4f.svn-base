package com.beidou.ybz.accountbook.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.PushModel;
import com.beidou.ybz.accountbook.ui.MainActivity;
import com.beidou.ybz.accountbook.ui.X5TitleWebActivity;
import com.umeng.message.UmengMessageService;

import org.android.agoo.common.AgooConstants;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * Author: ${Supreme} on 2018/1/9
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:
 */

public class MyPushIntentService extends UmengMessageService {

    @Override
    public void onMessage(Context context, Intent intent) {

        try {
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            LogUtils.loge("onMessage: ------message-------" + message);
            PushModel pushModel = GsonTools.getObject(message, PushModel.class);
//            UMessage msg = new UMessage(new JSONObject(message));
//            String title = msg.title;
//            String text = msg.text;
            String title = pushModel.getBody().getTitle();
            String text = pushModel.getBody().getText();
            String messageType = pushModel.getExtra().getMessageType();
            String url = pushModel.getExtra().getUrl();
            String param = pushModel.getExtra().getParam();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(R.mipmap.icon);

            if (messageType != null) {
//                switch (messageType) {
//                    case "StarbucksSignNotice":
//                        Intent intent1 = new Intent(context, X5WebActivity.class);
//                        intent1.putExtra("url", url);
//                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
//                        builder.setContentIntent(pendingIntent);
//                        break;
//                    case "h5":
//
//                        break;
//                    case "app":
//
//                        break;
//                }

                if (messageType.equals("StarbucksSignNotice")) {//星巴克签到
                    Intent intent1 = new Intent(context, X5TitleWebActivity.class);
                    intent1.putExtra("url", url);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
                    builder.setContentIntent(pendingIntent);
                    manager.notify(2, builder.build());
                } else if (messageType.equals("h5")) {//跳到h5页面
                    Intent intent1 = new Intent(context, X5TitleWebActivity.class);
                    intent1.putExtra("url", url);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    manager.notify(2, builder.build());
                } else if (messageType.equals("app")) {//跳到app
                    if (param != null) {
                        if (param.equals("appIndex")) {
                            Intent intent1 = new Intent(context, MainActivity.class);
                            intent1.putExtra("from", "main");
                            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, FLAG_UPDATE_CURRENT);
                            builder.setContentIntent(pendingIntent);
                            manager.notify(2, builder.build());
                        } else if (param.equals("fqIndex")) {
                            Intent intent1 = new Intent(context, MainActivity.class);
                            intent1.putExtra("from", "caishang");
                            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, FLAG_UPDATE_CURRENT);
                            builder.setContentIntent(pendingIntent);
                            manager.notify(2, builder.build());
                        } else if (param.equals("zzIndex")) {
                            Intent intent1 = new Intent(context, MainActivity.class);
                            intent1.putExtra("from", "zengzhi");
                            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, FLAG_UPDATE_CURRENT);
                            builder.setContentIntent(pendingIntent);
                            manager.notify(2, builder.build());
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
