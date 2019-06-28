package database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.trip.MainActivity
import java.io.*


class Database(context: Context, createTable: String, dropTable: String) :
    SQLiteOpenHelper(context, DATABASENAME, null, VERSION) {
    var createTable: String
    var dropTable: String
    lateinit var context : Context
    var DB_PATH = "/data/data/" + context.applicationContext.packageName + "/databases/" + DATABASENAME


    init {
        this.createTable = createTable
        this.dropTable = dropTable
        this.context  =context
        checkExists()

    }

    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(dropTable)
        onCreate(db)
    }

    companion object {
        private val ASSET_PATH = "database.db"
        private val TAG: String = "DatabaseHelper"
        val DATABASENAME = "database.db"
        val VERSION = 1
    }
    @Throws(IOException::class)
    private fun checkExists() {
//        Log.i(TAG, "checkExists()")

        val dbFile = File(DB_PATH)

        if (!dbFile.exists()) {

            Log.i(TAG, "creating database...")
            dbFile.parentFile.mkdirs()
            copyStream(context.assets.open(ASSET_PATH), FileOutputStream(dbFile))
            Log.i(TAG, ASSET_PATH + " has been copied to " + dbFile.absolutePath)

        }
    }

    @Throws(IOException::class)
    private fun copyStream(`is`: InputStream, os: OutputStream) {
        val buf = ByteArray(1024)
        var c = 0
        while (true) {
            c = `is`.read(buf)
            if (c == -1)
                break
            os.write(buf, 0, c)
        }
        `is`.close()
        os.close()
    }

}
