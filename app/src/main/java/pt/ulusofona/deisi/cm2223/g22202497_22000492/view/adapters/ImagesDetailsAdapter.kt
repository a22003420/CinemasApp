package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.databinding.ImageItemBinding

class ImagesDetailsAdapter(private val images: List<Uri>) : RecyclerView.Adapter<ImagesDetailsAdapter.ImageViewHolder>() {

  class ImageViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
    return ImageViewHolder(
      ImageItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent, false
      )
    )
  }

  override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
    holder.binding.apply {
      imageItem.setImageURI(images[position])
    }
  }

  override fun getItemCount() = images.size
}
