package ca.gbc.comp3074.borphukan_gargi.labex4

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//Data access obj provides abstration for all fun that will be used to access data-not for
//the query but also for inserts,updates delete

@Dao
interface CustomerDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)//alws start with insert
    suspend fun insert(customer: Customer)  //it's an interface

    @Delete
    suspend fun delete(customer: Customer)

    @Query("SELECT * FROM customers order by id") //make sure its imported
    fun getAll(): Flow<List<Customer>> //these query all customers

    //complex queries
    @Query("SELECT * FROM customers WHERE name LIKE '%' || :name || '%' ORDER BY name")
    fun getByName(name: String): Flow<List<Customer>> //flow directs the sqlite version that we quesry
// manually to replace the customer list .flow is creating a connection between the sqlite and the implememantton with the UI in compose

}