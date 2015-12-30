package com.example.ibagn.gmail;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MailList extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_mails_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton new_msg =(FloatingActionButton)findViewById(R.id.fab);
        new_msg.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

         getSupportActionBar().setTitle(" Primary");
        getSupportActionBar().setLogo(R.drawable.ham_menu);




        listView = (ListView) findViewById(R.id.listview);

        final ListViewItem[] items = new ListViewItem[10];


        for (int i = 0; i < items.length; i++) {
            if (i == 0) {
                items[i] = new ListViewItem("Social ","Google+","1 new" , CustomAdapter.TYPE_CATEGORIES);
            } else if (i == 1) {
                items[i] = new ListViewItem("Promotion ","Zagat, Google Offers","2 new" , CustomAdapter.TYPE_CATEGORIES);
            } else if (i == 2) {
                items[i] = new ListViewItem("Unpdates ","Google play","1 new" , CustomAdapter.TYPE_CATEGORIES);
            }  else if (i%2 == 0) {
                items[i] = new ListViewItem(R.drawable.drow,"Katniss Everdeen ",
                        "Supper tomorow "," Hungry people..",
                        "Work" , CustomAdapter.TYPE_MESSAGES);
            }
            else {
                items[i] = new ListViewItem(R.drawable.plinus,"Gandalf the White",
                        "Birthday party invitation","I shall celebrate my 1072..",
                        "Fun" , CustomAdapter.TYPE_MESSAGES);
            }

        }

        CustomAdapter customAdapter = new CustomAdapter(this, R.id.subject, items);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(items[i].getType()==CustomAdapter.TYPE_MESSAGES)

                {
                    TextView name = (TextView)findViewById(R.id.name);
                    TextView subject = (TextView) view.findViewById(R.id.subject);
                    TextView msg = (TextView) view.findViewById(R.id.msg);
                    TextView msg_type = (TextView) view.findViewById(R.id.masg_type);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        name.setTextAppearance(android.R.style.TextAppearance_Large);
                        subject.setTextAppearance(android.R.style.TextAppearance_Large);
                        msg.setTextAppearance(android.R.style.TextAppearance_Large);

                    }
                }

            }
        });

    }

    public class ListViewItem {
        private String title,subtitle,new_msg,name,subject,msg,msg_type;
        private int type,img;
        public ListViewItem(String title,String subtitle,String new_msg, int type) {
            this.img = img;
            this.title = title;
            this.subtitle = subtitle;
            this.new_msg = new_msg;
            this.type = type;
        }

        public ListViewItem(int img,String name, String subject, String msg, String msg_type, int type) {
            this.name = name;
            this.subject = subject;
            this.msg = msg;
            this.msg_type = msg_type;
            this.type = type;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMsg_type() {
            return msg_type;
        }

        public void setMsg_type(String msg_type) {
            this.msg_type = msg_type;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setNew_msg(String new_msg) {
            this.new_msg = new_msg;
        }

        public String getTitle() {

            return title;
        }

        public int getType() {
            return type;
        }

        public String getNew_msg() {
            return new_msg;
        }

        public String getSubtitle() {
            return subtitle;
        }





    }


    public class CustomAdapter extends ArrayAdapter<ListViewItem> {

        public static final int TYPE_CATEGORIES = 0;
        public static final int TYPE_MESSAGES = 1;


        private ListViewItem[] objects;

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return objects[position].getType();
        }

        public CustomAdapter(Context context, int resource, ListViewItem[] objects) {
            super(context, resource, objects);
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            ListViewItem listViewItem = objects[position];
            int listViewItemType = getItemViewType(position);


            if (convertView == null) {


                if (listViewItemType == TYPE_MESSAGES) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.messages, null);
                    TextView name = (TextView) convertView.findViewById(R.id.name);
                    TextView subject = (TextView) convertView.findViewById(R.id.subject);
                    TextView msg = (TextView) convertView.findViewById(R.id.msg);
                    TextView msg_type = (TextView) convertView.findViewById(R.id.masg_type);
                    ImageView img =(ImageView)convertView.findViewById(R.id.img);
                    msg_type.setBackgroundColor(getResources().getColor(R.color.blue));
                    viewHolder = new ViewHolder(img,name,subject,msg,msg_type);
                    viewHolder.getName().setText(listViewItem.getName());
                    viewHolder.getSubject().setText(listViewItem.getSubject());
                    viewHolder.getMsg().setText(listViewItem.getMsg());
                    viewHolder.getMsg_type().setText(listViewItem.getMsg_type());
                    if(position%2==1)
                    {viewHolder.getImg().setImageResource(R.drawable.plinus);}
                    else{viewHolder.getImg().setImageResource(R.drawable.drow);}


                } else if (listViewItemType == TYPE_CATEGORIES ) {

                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.categories, null);
                    TextView title = (TextView) convertView.findViewById(R.id.title);
                    TextView subtitle = (TextView) convertView.findViewById(R.id.subtitle);
                    TextView new_msg = (TextView) convertView.findViewById(R.id.new_msg);
                    ImageView icon = (ImageView)  convertView.findViewById(R.id.icon);
                    if(position==0){new_msg.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                    icon.setImageResource(R.drawable.social);}else
                    if(position==2){new_msg.setBackgroundColor(getResources().getColor(R.color.yellow));
                        icon.setImageResource(R.drawable.updates);}
                    else
                    if(position==1){new_msg.setBackgroundColor(getResources().getColor(R.color.green));
                        icon.setImageResource(R.drawable.promotions);}

                    viewHolder = new ViewHolder(icon,title,subtitle,new_msg);
                    viewHolder.getTitle().setText(listViewItem.getTitle());
                    viewHolder.getSubtitle().setText(listViewItem.getSubtitle());
                    viewHolder.getNew_msg().setText(listViewItem.getNew_msg());


                }




                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }


            return convertView;
        }

    }

    public class ViewHolder {
        TextView name,subject,msg,msg_type,
                title,subtitle, new_msg;
        ImageView img,icon;

        public ViewHolder(ImageView img,TextView name,TextView subject,TextView msg,TextView msg_type) {
            this.name = name;
            this.subject = subject;
            this.msg = msg;
            this.msg_type = msg_type;
            this.img = img;

        }

        public ImageView getImg() {
            return img;
        }

        public void setImg(ImageView img) {
            this.img = img;
        }

        public ViewHolder(ImageView icon, TextView title, TextView subtitle,  TextView new_msg) {
            this.title = title;
            this.subtitle = subtitle;
            this.new_msg = new_msg;
            this.icon = icon;
        }

        public TextView getNew_msg() {
            return new_msg;
        }

        public void setNew_msg(TextView new_msg) {
            this.new_msg = new_msg;
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(TextView subtitle) {
            this.subtitle = subtitle;
        }

        public TextView getMsg_type() {
            return msg_type;
        }

        public void setMsg_type(TextView msg_type) {
            this.msg_type = msg_type;
        }

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getMsg() {
            return msg;
        }

        public void setMsg(TextView msg) {
            this.msg = msg;
        }

        public TextView getSubject() {
            return subject;
        }

        public void setSubject(TextView subject) {
            this.subject = subject;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mails_list, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:

                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

}
