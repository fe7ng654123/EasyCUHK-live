package com.potechto.easycuhk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val blackboard: ImageView = findViewById(R.id.blackboard_icon)
        blackboard.setOnClickListener() {
            startWebviwer("https://blackboard.cuhk.edu.hk")
        }
        val library: ImageView = findViewById(R.id.library_icon)
        library.setOnClickListener() {
            startWebviwer("https://www.lib.cuhk.edu.hk")
        }
        val mycuhk: ImageView = findViewById(R.id.MyCUHK_icon)
        mycuhk.setOnClickListener() {
            startWebviwer("https://cusis.cuhk.edu.hk/psp/CSPRD/?cmd=login&languageCd=ENG&")
        }
        val ureply: ImageView = findViewById(R.id.ureply_icon)
        ureply.setOnClickListener() {
            val intent = Intent(this, Scanner::class.java)
            intent.putExtra("header", "ureply")
            startActivity(intent)
        }
        val keepoll: ImageView = findViewById(R.id.keepoll_icon)
        keepoll.setOnClickListener() {
            val intent = Intent(this, Scanner::class.java)
            intent.putExtra("header", "keepoll")
            startActivity(intent)
        }

        val bus: ImageView = findViewById(R.id.bus_icon)
        bus.setOnClickListener() {
            val intent = Intent(this, PictureViewer::class.java)
            startActivity(intent)
        }

        val calendar: ImageView = findViewById(R.id.calendar_icon)
        calendar.setOnClickListener() {
            startWebviwer("http://www.res.cuhk.edu.hk/en-gb/general-information/university-almanac/university-almanac-for-19-20")
        }

        val regcouse: ImageView = findViewById(R.id.reg_course_icon)
//        regcouse.setOnClickListener(){
//            startWebviwer("https://cusis.cuhk.edu.hk/psc/CSPRD/EMPLOYEE/HRMS/c/NUI_FRAMEWORK.PT_AGSTARTPAGE_NUI.GBL?CONTEXTIDPARAMS=TEMPLATE_ID%3aPTPPNAVCOL&scname=CS_SSR_MANAGE_CLASSES_NAV&PanelCollapsible=Y&PTPPB_GROUPLET_ID=MANAGECLASSES&CRefName=MANAGECLASSES")
//        }

        val map: ImageView = findViewById(R.id.map_icon)
        map.setOnClickListener() {
            startWebviwer("http://www.cuhk.edu.hk/english/campus/cuhk-campus-map.html")
        }

        val event: ImageView = findViewById(R.id.event_icon)
        event.setOnClickListener() {
            startWebviwer("https://webapp.itsc.cuhk.edu.hk/ras/restricted/MyEvent")
        }
    }

    public fun startWebviwer(url: String) {
        val intent = Intent(this, WebViewer::class.java)
        intent.putExtra("extra_url", url)
        startActivity(intent)
    }
}
