package com.example.unitybooks.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unitybooks.Description;
import com.example.unitybooks.Models.Books;
import com.example.unitybooks.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static com.example.unitybooks.Retrofit.RetrofitManager.BASE_URL;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.BooksViewHolder> {
    List<Books> bookList;
    Context context;

    public BookRecyclerAdapter(List<Books> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }
    //implementing ItemRecyclerAdapter on RecyclerView
    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_row, viewGroup, false);
        return new BooksViewHolder(itemview);
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    //this will set list of item and images to recycler view
    @Override
    public void onBindViewHolder(@NonNull final BooksViewHolder booksViewHolder, int i) {
        final Books books = bookList.get(i);
        String imgpath = BASE_URL + books.getBookImageName();
        System.out.println(imgpath);
        StrictMode();
        try {
            URL ur = new URL(imgpath);
            booksViewHolder.bimg.setImageBitmap(BitmapFactory.decodeStream((InputStream) ur.getContent()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        booksViewHolder.Book_Name.setText("Book_Name:" + books.getBookname());
        booksViewHolder.Book_author.setText("Address:" + books.getBookauthor());
        booksViewHolder.Book_price.setText("Description:" + books.getBookprice());
        Picasso.get().load(BASE_URL +"books/"+books.getBookImageName() ).into(booksViewHolder.bimg);
        Log.d("url",BASE_URL +"books/"+books.getBookImageName());
        booksViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Description.class);

                intent.putExtra("Book_Name", books.getBookname());
                intent.putExtra("Book_author", books.getBookauthor());
                intent.putExtra("Book_price", books.getBookprice());
                intent.putExtra("Book_img", books.getBookImageName());
//                intent.putExtra("Book_img", BASE_URL +"/books/" + venues.getBookImageName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
    //Creating abstarct class ItemViewHolder
    public class BooksViewHolder extends RecyclerView.ViewHolder {
        public TextView Book_Name;
        public TextView Book_author;
        public TextView Book_price;
        public ImageView bimg;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            Book_Name = itemView.findViewById(R.id.rvbookname);
            Book_author = itemView.findViewById(R.id.rvBookauthor);
            Book_price = itemView.findViewById(R.id.rvBookPrice);
            bimg= itemView.findViewById(R.id.rvimage);
        }
    }
}
