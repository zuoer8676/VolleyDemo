package com.example.volleydemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView lv_show;
	private List<String> list;   //存放网络图片地址
	private static ImageLoader imageLoader;//图片缓存器
	
	private MyAdapter myAdapter;  //listView适配器
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RequestQueue queue = Volley.newRequestQueue(this); //请求队列
		imageLoader = new ImageLoader(queue, new VolleyBitmapLruCache(this)); 
		//创建图片缓存所存放的文件夹
//		File f=new File("sdcard/abc");
//		if(!f.exists()){
//			f.mkdirs();
//		}
		lv_show=(ListView) findViewById(R.id.lv_show);
		list=new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("http://image3.uuu9.com/war3/dota2/UploadFiles/201303/_Z201303251348195461.jpg");//存放网络图片地址
			list.add("http://dotadb.uuu9.com/UploadFiles/Dota/Hero/klw.gif");
			list.add("http://dotadb.uuu9.com/UploadFiles/Dota/Hero/bingv.jpg");
			list.add("http://image3.uuu9.com/war3/dota2/UploadFiles/201303/_Z201303251348291251.jpg");
			list.add("http://dotadb.uuu9.com/UploadFiles/Dota/Hero/dfss.jpg");
			list.add("http://dotadb.uuu9.com/UploadFiles/Dota/Hero/hycm.jpg");
			list.add("http://dotadb.uuu9.com/UploadFiles/Dota/Hero/FW.gif");
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		if(myAdapter==null){
			myAdapter=new MyAdapter();
		}
		else{
			myAdapter.notifyDataSetChanged();
		}
		lv_show.setAdapter(myAdapter);
	}
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			Holder holder=null;
			if(convertView==null){
				holder=new Holder();
				convertView=getLayoutInflater().inflate(R.layout.lv_item,null);  //将子布局加入内存
				holder.iv_image=(NetworkImageView) convertView.findViewById(R.id.iv_image);  //找到图片
				convertView.setTag(holder);//将控件放入缓存
			}
			else{
				holder=(Holder) convertView.getTag();
			}
			holder.iv_image.setImageUrl(list.get(position), imageLoader);  //直接设置图片地址到NetworkImage,并将图片放入缓存
			holder.iv_image.setDefaultImageResId(R.drawable.ic_launcher);  //当图片还没加载出来时显示的图片
			return convertView;
		}
		
	}
	class Holder {
		NetworkImageView iv_image;
	}
	

}
