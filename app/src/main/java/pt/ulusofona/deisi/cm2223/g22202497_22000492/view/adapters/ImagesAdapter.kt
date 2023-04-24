package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.databinding.ImageItemWithDeleteBinding

class ImagesAdapter(private val images: MutableList<Uri>) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

  class ImageViewHolder(val binding: ImageItemWithDeleteBinding) : RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
    return ImageViewHolder(
      ImageItemWithDeleteBinding.inflate(
        LayoutInflater.from(parent.context),
        parent, false
      )
    )
  }

  override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
    holder.binding.apply {
      imageItem.setImageURI(images[position])

      deleteButton.setOnClickListener {
        onDeleteItem(position)
      }
    }
  }

  override fun getItemCount() = images.size

  private fun onDeleteItem(position: Int) {
    images.removeAt(position)
    notifyItemRemoved(position)
  }
}
