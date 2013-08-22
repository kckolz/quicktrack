package kolz.android.adapters;

import java.util.ArrayList;
import java.util.List;

import kolz.android.R;
import kolz.android.domain.Exercise;
import kolz.android.domain.Set;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ReviewAdapter extends BaseExpandableListAdapter{
	
	private List<Set> sets;
	Context context;
	private List<String> groups;
	private ArrayList<ArrayList<Set>> children;

	public ReviewAdapter(Context context, ArrayList<String> groups,
            ArrayList<ArrayList<Set>> children) {
        this.context = context;
        this.groups = groups;
        this.children = children;
    }
	
	public void addItem(Set set) {
        if (!groups.contains(set.getTimeStamp().toString())) {
            groups.add(set.getTimeStamp().toString());
        }
        int index = groups.indexOf(set.getTimeStamp().toString());
        if (children.size() < index + 1) {
            children.add(new ArrayList<Set>());
        }
        children.get(index).add(set);
    }
	

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return children.get(groupPosition).get(childPosition);
	}

	@Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
		return null;
//        Set set = (Set) getChild(groupPosition, childPosition);
//        if (convertView == null) {
//            LayoutInflater infalInflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = infalInflater.inflate(R.layout.reviewlist, null);
//        }
//        TextView tv = (TextView) convertView.findViewById(R.id.tvChild);
//        tv.setText("   " + vehicle.getName());
//
//        // Depending upon the child type, set the imageTextView01
//        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//        if (vehicle instanceof Car) {
//            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.car, 0, 0, 0);
//        } else if (vehicle instanceof Bus) {
//            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bus, 0, 0, 0);
//        } else if (vehicle instanceof Bike) {
//            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bike, 0, 0, 0);
//        }
//        return convertView;
    }

	@Override
	public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

	@Override
	public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

	@Override
	public int getGroupCount() {
        return groups.size();
    }

	@Override
	public long getGroupId(int groupPosition) {
        return groupPosition;
    }

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
	 
}