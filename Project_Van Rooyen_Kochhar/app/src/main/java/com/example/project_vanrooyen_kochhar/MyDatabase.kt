import android.content.Context
import androidx.room.*
import com.example.project_vanrooyen_kochhar.Student
import com.example.project_vanrooyen_kochhar.StudentDao


@Database(entities = arrayOf(Student::class) , version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun  studentDao() : StudentDao
    companion object {
        private var instance: MyDatabase? = null
        fun getDatabase(context: Context): MyDatabase? {
            if (instance == null) {
                synchronized(MyDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "MyDB"
                    ).allowMainThreadQueries().build()

                }

            }

            return instance
        }
    }
}