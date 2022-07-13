package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter (
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }
    fun AddTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }
    fun Delete_item(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun enableStrikeThrough(todo_title: TextView, isChecked:Boolean){
        if (isChecked){
            todo_title.paintFlags = todo_title.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            todo_title.paintFlags = todo_title.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var CursorTodo = todos[position]
        holder.itemView.apply {
            todo_title.text = CursorTodo.title
            done_item.isChecked = CursorTodo.isChecked
            enableStrikeThrough(todo_title, CursorTodo.isChecked)
            done_item.setOnCheckedChangeListener{ _, isChecked ->
                enableStrikeThrough(todo_title, isChecked)
                CursorTodo.isChecked = !CursorTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}