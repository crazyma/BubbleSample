package com.beibeilab.bubblesample.ui.main

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Icon
import android.media.RingtoneManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.beibeilab.bubblesample.MainActivity
import com.beibeilab.bubblesample.R
import com.beibeilab.bubblesample.ui.bubble.BubbleActivity
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {

        private const val CHANNEL_NAME = "Bubble"
        private const val CHANNEL_ID = "bubble_channel"

        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun setupViews() {
        button.setOnClickListener {
            launchBubble()
        }
    }

    private fun launchBubble() {

        val notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)

        // Create bubble intent
        val target = Intent(context, BubbleActivity::class.java)
        val bubbleIntent = PendingIntent.getActivity(context, 0, target, 0 /* flags */)

        // Create bubble metadata
        val bubbleData = Notification.BubbleMetadata.Builder()
            .setDesiredHeight(600)
            .setIcon(Icon.createWithResource(context!!, R.drawable.ic_android_black_24dp))
            .setIntent(bubbleIntent)
            .setAutoExpandBubble(true)
            .build()

        // Create notification
        val chatBot = Person.Builder()
            .setBot(true)
            .setName("BubbleBot")
            .setImportant(true)
            .build()

        val builder = Notification.Builder(context!!, CHANNEL_ID)
            .setContentTitle("Your to-do list is here")
            .setContentText("Click to open to-do list")
            .setContentIntent(bubbleIntent)
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setBubbleMetadata(bubbleData)
            .setAutoCancel(true)
            .addPerson(chatBot)

        val notificationId = 1
        val notification = builder.build()

        notificationManager.notify(notificationId, notification)
    }

    private fun launchNotification() {
        val context = context!!
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val builder =
            NotificationCompat.Builder(context!!, "channel01")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("My notification")
                .setContentText("Hello World")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(longArrayOf(300, 600, 300, 600))
                .setLights(Color.GREEN, 1000, 1000)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        val channel = NotificationChannel(
            "channel01",
            "MyChannel",
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationId = 1
        val notification = builder.build()

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(notificationId, notification)
    }

}
