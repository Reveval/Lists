package by.mbicycle.develop.lists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

//Recycler View
class MainActivity : AppCompatActivity() {
    val states = ArrayList<State>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //начальная инициализация списка
        setInitialData()
        val recyclerView = findViewById<RecyclerView>(R.id.list)
        //определяем слушателя нажатия элемента в списке
        val stateClickListener = object : StateAdapter.OnStateClickListener {
            override fun OnStateClick(state: State, position: Int) {
                Toast.makeText(applicationContext, "Был выбран пункт ${state.name}",
                    Toast.LENGTH_SHORT).show()
            }

        }

        //создаем адаптер
        val adapter = StateAdapter(this, states, stateClickListener)
        //устанавливаем для списка адаптер
        recyclerView.adapter = adapter
    }

    /*
        С помощью метода setInitialData() устанавливается набор начальных данных. В данном случае
            имеется в виду, что в папке res/drawables находится ряд ресурсов изображений для
            объектов State.
     */
    private fun setInitialData() {
        states.apply {
            add(State("Бразилия", "Бразилиа", R.drawable.brazil))
            add(State("Аргентина", "Буэнос-Айрес", R.drawable.argentina))
            add(State("Колумбия", "Богота", R.drawable.columbia))
            add(State("Уругвай", "Монтевидео", R.drawable.urugvai))
            add(State("Чили", "Сантьяго", R.drawable.chili))
        }
    }
}