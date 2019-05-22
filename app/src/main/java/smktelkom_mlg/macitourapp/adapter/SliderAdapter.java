package smktelkom_mlg.macitourapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import smktelkom_mlg.macitourapp.R;

/**
 * Created by MirzaUY on 3/5/2018.
 */

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){
        this.context = context;
    }
    public String[] slideTextSatu ={ "Selamat Datang !" , "Pariwisata","Budaya"};
    public String[] slideTextDua ={
            "Di Macitour" ,
            "Kota Malang memiliki banyak tempat pariwisata yang harus dirawat ",
            "Bukan hanya pariwisata saja. Kota Malang juga memiliki kearifan lokal yang beragam"};
    public String[] slideTextTiga ={
            "Ngalam City !" ,
            "Kita Harus Bangga !",
            "Jagalah Budaya kita !"};
    public String[] slideTextEmpat ={
            "Malang merupakan sebuah daerah yang berada di Jawa Timur yang memiliki keanekaragaman tempat pariwisata dan kearifan lokal yang cukup membanggakan." ,
            "Dengan kearifan lokal yang kaya dan tempat pariwasata yang bervariasi kita harus bangga dengan hal tersebut",
            "Tidak hanya menikmati saja kita harus menjaga dan melestarikan kearifan lokal dan tempat pariwisata"};


    @Override
    public int getCount() {
        return slideTextSatu.length;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);
        TextView tvSatu = (TextView) view.findViewById(R.id.txtSatu);
        TextView tvDua = (TextView) view.findViewById(R.id.txtDua);
        TextView tvTiga = (TextView) view.findViewById(R.id.txtTiga);
        TextView tvEmpat = (TextView) view.findViewById(R.id.txtEmpat);
        tvSatu.setText(slideTextSatu[position]);
        tvDua.setText(slideTextDua[position]);
        tvTiga.setText(slideTextTiga[position]);
        tvEmpat.setText(slideTextEmpat[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
