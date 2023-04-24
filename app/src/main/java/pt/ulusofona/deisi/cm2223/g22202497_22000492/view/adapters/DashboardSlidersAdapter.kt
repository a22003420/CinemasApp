package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.databinding.SliderItemBinding
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie

class DashboardSlidersAdapter(
  private val onClick: (String) -> Unit,
  private var items: List<Movie>
) : RecyclerView.Adapter<DashboardSlidersAdapter.DashboardSlidersHolder>() {

  class DashboardSlidersHolder(val binding: SliderItemBinding) : RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardSlidersHolder {
    return DashboardSlidersHolder(
      SliderItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent, false
      ))
  }

  override fun onBindViewHolder(holder: DashboardSlidersHolder, position: Int) {
    holder.itemView.setOnClickListener { onClick(items[position].getId().toString()) }
    val item = items[position]

    holder.binding.apply {
      val resourceId = holder.itemView.context.resources.getIdentifier(item.getPhoto(), "drawable",  holder.itemView.context.packageName)
      sliderItem.setImageResource(resourceId)
    }
  }

  override fun getItemCount() = items.size
}
