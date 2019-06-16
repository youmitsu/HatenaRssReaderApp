package youmeee.co.jp.hatenarssreaderapp

import android.preference.PreferenceManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        val isContainsKey = remoteMessage?.data?.containsKey("CONFIG_STATE") ?: return
        if (isContainsKey) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            // CONFIG値が古いことを表すフラグをtrueにする
            sharedPreferences.edit().putBoolean("CONFIG_STALE", true).apply()
        }
    }
}