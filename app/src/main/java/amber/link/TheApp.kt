package amber.link

import android.app.Application
import android.content.Context

/*
* Created by Amber Yiyao Zhou & Link Zhou Fang on December 02, 2019
*/

class TheApp: Application() {

    //region method

    override fun onCreate(){
        super.onCreate()
        context = applicationContext
    }
    companion object {
        lateinit var context: Context
            private set
    }

    //endregion

}