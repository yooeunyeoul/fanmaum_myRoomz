package com.fanmaum.myroomz.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessaging : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.e("FIrebaseToken" ,"$token")
        super.onNewToken(token)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
    }
}