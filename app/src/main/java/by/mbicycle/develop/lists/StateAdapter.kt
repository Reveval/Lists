package by.mbicycle.develop.lists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
    Как и в случае с ListView, для вывода сложных объектов в RecyclerView необходимо определить
        свой адаптер. Поэтому добавим в ту же папку, где расположен класс MainActivity и
        State, новый класс Java, который назовем StateAdapter. Адаптер, который используется
        в RecyclerView, должен наследоваться от абстрактного класса RecyclerView.Adapter.
 */
class StateAdapter(context: Context, states: List<State>, onClickListener: OnStateClickListener) :
    RecyclerView.Adapter<StateAdapter.ViewHolder>() {
    //Определяем интерфейс слушателя события нажатия.
    interface OnStateClickListener {
        fun OnStateClick(state: State, position: Int)
    }

    val mStates = states
    val mInflater = LayoutInflater.from(context)
    val mOnClickListener = onClickListener

    /*
        onCreateViewHolder: возвращает объект ViewHolder, который будет хранить данные по
            одному объекту Phone.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    /*
        onBindViewHolder: выполняет привязку объекта ViewHolder к объекту Phone по определенной
            позиции.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state = mStates[position]
        holder.apply {
            flagView.setImageResource(state.flag)
            nameView.text = state.name
            capitalView.text = state.capital

            /*
                Класс ViewHolder имеет поле itemView, которое представляет интерфейс для одного
                    объекта в списке и фактически объект View. А у этого объекта есть метод
                    setOnClickListener(), через который можно подлючить стандартный слушатель
                    нажатия OnClickListener и в его методе onClick() вызвать метод нашего
                    интерфейса, передав ему необходимые данные - выбранный объект State и его
                    позицию в списке.
             */
            itemView.setOnClickListener { mOnClickListener.OnStateClick(state, position) }
        }
    }

    //getItemCount: возвращает количество объектов в списке
    override fun getItemCount(): Int {
        return mStates.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val flagView = view.findViewById<ImageView>(R.id.flag)
        val nameView = view.findViewById<TextView>(R.id.name)
        val capitalView = view.findViewById<TextView>(R.id.capital)
    }
}