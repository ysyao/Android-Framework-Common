package cn.sccl.ilife.android.ui.sample.picturelist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.core.ImageLoder.ImageLoaderUtils;
import cn.sccl.ilife.android.sample.picturelist.BookUtils;
import cn.sccl.ilife.android.sample.picturelist.DouBanBook;
import cn.sccl.ilife.android.sample.picturelist.DouBanBookList;
import cn.sccl.ilife.android.ui.BaseListAdapter;

public class ItemAdapter extends BaseListAdapter<DouBanBook> {
    class ViewHolder {
        ImageView bookImage;
        TextView bookTitle;
        TextView bookIntro;
        RatingBar bookRating;
    }
    public ItemAdapter(Context context, List<DouBanBook> items) {
        super(context, items);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            view = getInflater().inflate(R.layout.item_douban_book_list, viewGroup, false);
            holder = new ViewHolder();
            holder.bookImage = (ImageView) view.findViewById(R.id.book_image);
            holder.bookTitle = (TextView) view.findViewById(R.id.book_title);
            holder.bookIntro = (TextView) view.findViewById(R.id.book_intro);
            holder.bookRating = (RatingBar) view.findViewById(R.id.book_rating);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        DouBanBook book = getItem(i);
        holder.bookTitle.setText(book.getTitle());
        holder.bookIntro.setText(book.getSummary());
        holder.bookRating.setRating(BookUtils.countingBookRating(book));
        holder.bookImage.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.ic_av_surround_sound));

//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
//        ImageLoader imageLoader = ImageLoader.getInstance();
//        imageLoader.init(config);
        ImageLoader imageLoader = ImageLoaderUtils.getImageLoader(getApplicationContext());
        imageLoader.displayImage(book.getImages().getMedium(), holder.bookImage);
        return view;
    }
}
