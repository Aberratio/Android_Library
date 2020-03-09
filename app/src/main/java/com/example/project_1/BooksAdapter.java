package com.example.project_1;

import android.content.Context;
import android.graphics.ImageDecoder;
import android.nfc.Tag;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.squareup.picasso.Picasso;

import java.io.File;

import static android.graphics.ImageDecoder.createSource;

public class BooksAdapter extends BaseAdapter {

    private final Context mContext;
    private final Book[] books;
    private String galleryPath;
    private File photoGallery;
    private String[] photosInGallery;
    final String path = android.os.Environment.DIRECTORY_DCIM;

    // 1
    public BooksAdapter(Context context, Book[] books) {
        this.mContext = context;
        this.books = books;
    }

    // 2
    @Override
    public int getCount() {
        return books.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Book book = books[position];

        // view holder pattern
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_book, null);

            final ImageView imageViewCoverArt = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
            final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_book_name);
            final TextView authorTextView = (TextView)convertView.findViewById(R.id.textview_book_author);
            final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);

            final ViewHolder viewHolder = new ViewHolder(nameTextView, authorTextView, imageViewCoverArt, imageViewFavorite);
            convertView.setTag(viewHolder);
        }



        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        //Picasso.with(mContext).load(book.getImageUrl()).into(viewHolder.imageViewCoverArt);
        Picasso.with(mContext).load(book.getImageResource()).into(viewHolder.imageViewCoverArt);
        //Picasso.with(mContext).load(new File("Phone/DCIM")).into(viewHolder.imageViewCoverArt);
        //Picasso.with(mContext).load("https://fwcdn.pl/fpo/67/64/836764/7902869.3.jpg").into(viewHolder.imageViewCoverArt);
        //viewHolder.imageViewCoverArt.setImageResource(book.getImageResource());
        viewHolder.nameTextView.setText(mContext.getString(book.getName()));
        viewHolder.authorTextView.setText(mContext.getString(book.getAuthor()));
        viewHolder.imageViewFavorite.setImageResource(book.getIsFavorite() ? R.drawable.star_enabled : R.drawable.star_disabled);

        //MediaStore.Images.Media.getContentUri("");

        ImageDecoder.Source imageSource = ImageDecoder.createSource(getGalleryFolder());
        //imageSource.

        return convertView;
    }


    // Your "view holder" that holds references to each subview
    private class ViewHolder {
        private final TextView nameTextView;
        private final TextView authorTextView;
        private final ImageView imageViewCoverArt;
        private final ImageView imageViewFavorite;

        public ViewHolder(TextView nameTextView, TextView authorTextView, ImageView imageViewCoverArt, ImageView imageViewFavorite) {
            this.nameTextView = nameTextView;
            this.authorTextView = authorTextView;
            this.imageViewCoverArt = imageViewCoverArt;
            this.imageViewFavorite = imageViewFavorite;
        }
    }

    File getGalleryFolder() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        if (!folder.exists()) {
            folder.mkdir();
        }
        // /storage/emulated/0/DCIM
        return folder;
    }

}

