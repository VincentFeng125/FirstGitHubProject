
package com.example.higit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ClassName:NearByPoiAdapter
 * 
 * @author Vincent Feng
 */
public class NearByPoiAdapter extends BaseAdapter implements OnClickListener {

	private Map<String , List<PoiNearbyKeyword>> mDatas;

	private IItemClickListener mClickListener;

	private static final int MAX = 3;

	private List<String> mKeys;

	public NearByPoiAdapter(Map<String , List<PoiNearbyKeyword>> data, IItemClickListener listener) {

		this.mDatas = data;
		this.mClickListener = listener;
		Iterator<String> iterator = data.keySet().iterator();
		this.mKeys = new ArrayList<String>();
		while (iterator.hasNext()) {
			this.mKeys.add(iterator.next());
		}
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(mKeys.get(position));
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		BasicHolder basicHolder = null;

		int itemCount = mDatas.get(mKeys.get(position)).size();

		if (convertView == null) {
			basicHolder = new BasicHolder();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_around_keyword_item, null);

			basicHolder.img = (ImageButton) convertView.findViewById(R.id.imb_nearby_type);
			basicHolder.childRootLayout = (LinearLayout) convertView.findViewById(R.id.nearby_keyword);
			basicHolder.tv1 = (TextView) convertView.findViewById(R.id.textView1);
			basicHolder.tv2 = (TextView) convertView.findViewById(R.id.textView2);
			basicHolder.tv3 = (TextView) convertView.findViewById(R.id.textView3);
			basicHolder.tv1.setOnClickListener(this);
			basicHolder.tv2.setOnClickListener(this);
			basicHolder.tv3.setOnClickListener(this);
			convertView.setTag(basicHolder);
		} else {
			basicHolder = (BasicHolder) convertView.getTag();
		}

		int average = itemCount / MAX;

		if (average == 0) {
			
			switch (itemCount % MAX) {
			case 1:
				basicHolder.tv1.setText(mDatas.get(mKeys.get(position)).get(0).getKeyWordType());
				break;
			case 2:
				basicHolder.tv1.setText(mDatas.get(mKeys.get(position)).get(0).getKeyWordType());
				basicHolder.tv2.setText(mDatas.get(mKeys.get(position)).get(1).getKeyWordType());
				break;
			}
			
		} else {
		
			basicHolder.tv1.setText(mDatas.get(mKeys.get(position)).get(0).getKeyWordType());
			basicHolder.tv2.setText(mDatas.get(mKeys.get(position)).get(1).getKeyWordType());
			basicHolder.tv3.setText(mDatas.get(mKeys.get(position)).get(2).getKeyWordType());
			
			for (int i = 1; i < average; i++) {

				View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_around_keyword_view, null);
				basicHolder.childRootLayout.addView(view);

				((TextView) view.findViewById(R.id.textView1)).setOnClickListener(this);
				((TextView) view.findViewById(R.id.textView2)).setOnClickListener(this);
				((TextView) view.findViewById(R.id.textView3)).setOnClickListener(this);

				((TextView) view.findViewById(R.id.textView1)).setText(mDatas.get(mKeys.get(position)).get(i * MAX + 0).getKeyWordType());
				((TextView) view.findViewById(R.id.textView2)).setText(mDatas.get(mKeys.get(position)).get(i * MAX + 1).getKeyWordType());
				((TextView) view.findViewById(R.id.textView3)).setText(mDatas.get(mKeys.get(position)).get(i * MAX + 2).getKeyWordType());
			}

		}

		return convertView;
	}

	private class BasicHolder {
		TextView tv1, tv2, tv3;
		ImageButton img;
		LinearLayout childRootLayout;
	}

	@Override
	public void onClick(View v) {
		String tmpString = ((TextView) v).getText().toString();
		if (mClickListener != null && !tmpString.trim().isEmpty()) {
			mClickListener.onItemClick(tmpString);
		}

	}

}
