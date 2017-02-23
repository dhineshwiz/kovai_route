package com.example.bus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class NextDisplay extends Activity {
	SQLiteDatabase db;
	String src;
	String des;
	int i=0;
	int count=0;
	String dbn="fbusdb1.sqlite";
	Cursor cur,cur1,cur2,cur3;
	  String [] srt=new String[50];
	  String [] drt=new String[50];
	String [] sr=new String[50];
	String [] dr=new String[50];
	String [] bs=new String[150];
	String [] fbs=new String[150];
	String [] bs1=new String[150];
	String [] fbs1=new String[150];
	String [] bd=new String[150];
	String [] fbd=new String[150];
	String [] bd1=new String[150];
	String [] fbd1=new String[150];
	String [] rsrc=new String[200];
	String [] busno=new String[50];
	String [] rdes=new String[200];
	String [][] srtn=new String[50][50];
	String [][] drtn=new String[50][50];
	String n;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme);
		setContentView(R.layout.next);
		String[] myarray =getResources().getStringArray(R.array.spinner_item);
	Button mButton = (Button)findViewById(R.id.button1);
 //   final EditText sEdit = (EditText)findViewById(R.id.editText2);
    final AutoCompleteTextView sEdit = (AutoCompleteTextView) findViewById(R.id.editText2);
    //final EditText dEdit = (EditText)findViewById(R.id.editText1);
    final AutoCompleteTextView dEdit = (AutoCompleteTextView) findViewById(R.id.editText1);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.select_dialog_item,myarray);
    sEdit.setThreshold(1);
    dEdit.setThreshold(1);
    sEdit.setAdapter(adapter);
    dEdit.setAdapter(adapter);
    		   mButton.setOnClickListener( new OnClickListener()  {
		         public void onClick(View view)
		            {
		        	 for(i=0;i<50;i++){
		        	    	srt[i]=null;
		        	    }
		        	    for(i=0;i<50;i++){
		        	    	drt[i]=null;
		        	    }
		        	    i=0;
		        	 //System.out.println("clcked");
		        	    String src=null;
		        	     String des=null;
		        src=sEdit.getText().toString();
		        		 des=dEdit.getText().toString();
		        		 if((!des.isEmpty())&& (src.isEmpty())){

			        			Toast.makeText(getBaseContext(),"Source cannot be empty!", 
			 							Toast.LENGTH_LONG).show();
		        		 }
		        		 if((!src.isEmpty())&&(des.isEmpty())){
			        			Toast.makeText(getBaseContext(),"Destination cannot be empty!", 
			 							Toast.LENGTH_LONG).show();
		        		 }
		        		 if((src.isEmpty())&&(des.isEmpty())){
			        			Toast.makeText(getBaseContext(),"Source and destination cannot be empty!", 
			 							Toast.LENGTH_LONG).show();
			        		}
		        		 else if((src.equals(des)==true)&&((!src.isEmpty())&&(!des.isEmpty()))){
		        			//System.out.println("same");
		        			Toast.makeText(getBaseContext(),"Source and destination cannot be same!", 
		 							Toast.LENGTH_LONG).show();
		        		}
		        		
		        		else if((!src.isEmpty())&&(!des.isEmpty())){
		        		    db = openOrCreateDatabase("fbusdb1.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
		    db.setVersion(1);
		    db.setLocale(Locale.getDefault());
		    //src="Ganapathy";
		    //String q1="select bus_no from bus_det where bus_no ='"+src+"'";
		    String q="select Route from route_table where '"+src+"' IN (Starting_point,stop1,stop2,stop3,stop4,stop5,stop6,stop7);";
		    cur = db.rawQuery(q,null );
		    String q1="select Route from route_table where '"+des+"' IN (Starting_point,stop1,stop2,stop3,stop4,stop5,stop6,stop7);";
		    cur1 = db.rawQuery(q1,null );
		    cur.moveToFirst();
		    int c=cur.getCount();
		    int c1=cur1.getCount();
		    /*cur.moveToFirst();
		    System.out.println("pp"+c);*/
		    cur1.moveToFirst();  
		       while(!cur.isAfterLast()){//source route numbers
			      	String n = cur.getString(0).toString();
			      	srt[i]=n;
			      //	System.out.println("s"+srt[i]);
			      	i++;
			    cur.moveToNext();
			    }
		       cur.close();
		       i=0;
		       while(!cur1.isAfterLast()){//destination route numbers
		    	   String n = cur1.getString(0).toString();
		    	   drt[i]=n;
		    	    //System.out.println("d"+drt[i]);
		    	    i++;
			    cur1.moveToNext();
			    }
		       cur1.close();
/*		       i=0;
		       int j=0;
		       int k=0;
		       int t=0;
		       while(srt[i]!=null){//retrieving bus no for the source
		    	   //System.out.println("pp"+srt[i]);
		    	 String temp =srt[i];
		    	 //temp="R2";
		          String q3="select bus_no from Bustable where '"+temp+"' IN(Route1,Route2,Route3,Route4,Route5,Route6,Route7,Route8,Route9,Route10,Route11,Route12,Route13,Route14,Route15,Route16,Route17,Route18,Route19,Route20,Route21,Route22,Route23,Route24);";
		          cur2=db.rawQuery(q3,null);
		          cur2.moveToFirst();
		          int c1=cur2.getCount();
		          //System.out.println(c1);
		          if(cur2!=null){
		        	  cur2.moveToFirst();
		        	  t=j;
		            while(!cur2.isAfterLast()){
		        	  String n=cur2.getString(0).toString();
       	     		  bs[j]=n;
		        	  //System.out.println("sstr"+bs[j]);
		        		  j++;
		       	  cur2.moveToNext();
		          }
		          }
		          //i++;
		           
		          String temp1 ="~"+srt[i];
			    	// temp="R2";	
			           q3="select bus_no from Bustable where '"+temp1+"' IN(Route1,Route2,Route3,Route4,Route5,Route6,Route7,Route8,Route9,Route10,Route11,Route12,Route13,Route14,Route15,Route16,Route17,Route18,Route19,Route20,Route21,Route22,Route23,Route24);";
			          cur2=db.rawQuery(q3,null);
			          cur2.moveToFirst();
			          cur2.getCount();
			          //System.out.println(c1);
			          if(cur2!=null){
			        	  cur2.moveToFirst();
			        	  t=k;
			        	  
			             while(!cur2.isAfterLast()){
			            	 String n=cur2.getString(0).toString();
			        	  	   bs1[k]=n;
			        	//  System.out.println("srev"+bs1[k]);
			        		  k++;
			       	  cur2.moveToNext();
			          }
			          }
			          i++;
		            }
		       cur2.close();
		       i=0;
		       j=0;
		       k=0;
		       while(drt[i]!=null){//retrieving bus no for the destination
		    	   //System.out.println("pp"+drt[i]);
		    	 String temp =drt[i];
		    	 //temp="R2";
		          String q3="select bus_no from Bustable where '"+temp+"' IN(Route1,Route2,Route3,Route4,Route5,Route6,Route7,Route8,Route9,Route10,Route11,Route12,Route13,Route14,Route15,Route16,Route17,Route18,Route19,Route20,Route21,Route22,Route23,Route24);";
		          cur3=db.rawQuery(q3,null);
		          cur3.moveToFirst();
		          int c1=cur3.getCount();
		          //System.out.println(c1);
		          if(cur3!=null){
		          cur3.moveToFirst();
		             while(!cur3.isAfterLast()){
		        	  String n=cur3.getString(0).toString();
		        	   bd[j]=n;
		        	  //System.out.println("dstr"+bd[j]);
		        		  j++;
		       	  cur3.moveToNext();
		          }
		          }
		          //i++;
		          String temp1 ="~"+drt[i];
			    	// temp="R2";	
			           q3="select bus_no from Bustable where '"+temp1+"' IN(Route1,Route2,Route3,Route4,Route5,Route6,Route7,Route8,Route9,Route10,Route11,Route12,Route13,Route14,Route15,Route16,Route17,Route18,Route19,Route20,Route21,Route22,Route23,Route24);";
			          cur3=db.rawQuery(q3,null);
			          cur3.moveToFirst();
			          cur3.getCount();
			          //System.out.println(c1);
			          if(cur3!=null){
			          cur3.moveToFirst();
			   
			             while(!cur3.isAfterLast()){
			        	  String n=cur3.getString(0).toString();
			        	   bd1[k]=n;
			        	//  System.out.println("drev"+bd1[k]);
			        		  k++;
			       	  cur3.moveToNext();
			          }
			          }
			          i++;
		            }
		       cur3.close();
		       j=0;
		     for(i=0;bs[i]!=null;i++)
		     {
		    	 rsrc[j]=bs[i];
		    	 j++;
		     }
		     for(i=0;bs1[i]!=null;i++){
		    	 rsrc[j]=bs1[i];
		    	 j++;
		     }
		     for(i=0;rsrc[i]!=null;i++)
		     {
		    	 //System.out.println("final"+rsrc[i]);
		     }
		     int i = 0;
		     Set<String> tmp = new LinkedHashSet<String>();
		       for (String each : rsrc) {
		           tmp.add(each);
		       }
		       for (String each : tmp) {
		           fbs[i++] = each;
		       }
		       for(i=0;fbs[i]!=null;i++)
		       System.out.println("sr"+fbs[i]);
		       
		       j=0;
			     for(i=0;bd[i]!=null;i++)
			     {
			    	 rdes[j]=bd[i];
			    	 j++;
			     }
			     for(i=0;bd1[i]!=null;i++){
			    	 rdes[j]=bd1[i];
			    	 j++;
			     }
			     for(i=0;rdes[i]!=null;i++)
			     {
			    	// System.out.println("final"+rdes[i]);
			     }
			      i = 0;
			     Set<String> tmp1 = new LinkedHashSet<String>();
			       for (String each : rdes) {
			           tmp1.add(each);
			       }
			       for (String each : tmp1) {
			           fbd[i++] = each;
			       }
			       for(i=0;fbd[i]!=null;i++)
			       System.out.println("dr"+fbd[i]);
			    k=0;  
	       for(i=0;fbs[i]!=null;i++){
	    	 for(j=0;fbd[j]!=null;j++){
	   		   if(fbs[i].equals(fbd[j])==true){
	   			   busno[k]=fbs[i];
			  	     System.out.println("b"+busno[k]);
			  	   k++;
			   		   }
			   	   }
     }
	       Intent intent = new Intent(getBaseContext(),Gbdisplay.class);
	       intent.putExtra("busno", busno);
	       intent.putExtra("src",src);
	       intent.putExtra("des",des);
	       startActivity(intent);
	       
	    /*     Set<String> tmp = new LinkedHashSet<String>();
		       for (String each : bs) {
		           tmp.add(each);
		       }
		       for (String each : tmp) {
		           fbs[i++] = each;
		       }
		       for(i=0;fbs[i]!=null;i++)
		       System.out.println("sr"+fbs[i]);
		      
		       Set<String> tmp1 = new LinkedHashSet<String>();
		       for (String each : bs1) {
		           tmp1.add(each);
		       }
		       i = 0;
		       for (String each : tmp1) {
		           fbs1[i++] = each;
		       }
		       for(i=0;fbs1[i]!=null;i++)
		       System.out.println("r"+fbs1[i]);
		       
		       Set<String> tmp3 = new LinkedHashSet<String>();
		       for (String each : bd) {
		           tmp3.add(each);
		       }
		       i = 0;
		       for (String each : tmp3) {
		           fbd[i++] = each;
		       }
		       for(i=0;fbd[i]!=null;i++)
		       System.out.println("dr"+fbd[i]);
		       
		       Set<String> tmp2 = new LinkedHashSet<String>();
		       for (String each : bd1) {
		           tmp2.add(each);
		       }
		       i = 0;
		       for (String each : tmp2) {
		           fbd1[i++] = each;
		       }
		       for(i=0;fbd1[i]!=null;i++)
		       System.out.println("r"+fbd1[i]);*/
		 		/*       while(srt[i]!=null){//source route names
		    	   int j=1;
		    	   int k=0;
		    	   String temp=srt[i];
		    	   String q2="select * from route_table where Route='"+temp+"'";
		    		cur2=db.rawQuery(q2,null);
		    		cur2.moveToFirst();
		    		do{
		    			String n=cur2.getString(j).toString();
		    			srtn[i][k]=n;
		    			System.out.println(srtn[i][k]);
		    			k++;
		    			j++;
		    		}while(cur2.getString(j)!=null);
		    		i++;
		       }
		       int l;
		       sr[0]=srtn[0][0];
		       int r=1;
		       int k=0;
		       int j;
			    for(i=1,j=0;srtn[i][j]!=null;i++){//removing repeatition of junction points
			    	for(l=i-1,k=1;srtn[l][k]!=null;k++){
			    	  		if(srtn[l][k].equals(srtn[i][j])==true){
			    			
			    			sr[r]=srtn[l][k];
			    		//	System.out.println("same"+sr[r]);
			    			r++;
			    		}
			    		else if(srtn[l][k].equals(srtn[i][j])==false)
			    		{
			    			sr[r]=srtn[l][k];
			    	//		System.out.println("bb"+sr[r]);
			    			r++;
			    		}
		   	    	}
			    }
			    i--;
			    while(srtn[i][j]!=null){
			    	sr[r]=srtn[i][j];
			    	j++;
			    }
			    i=0;
			    while(sr[i]!=null){
			    System.out.println("sfinal"+sr[i]);
			    i++;
			    }
			    i=0;
			       while(drt[i]!=null){//source route names
			    	    j=1;
			    	    k=0;
			    	   String temp=drt[i];
			    	   String q2="select * from route_table where Route='"+temp+"'";
			    		cur2=db.rawQuery(q2,null);
			    		cur2.moveToFirst();
			    		do{
			    			String n=cur2.getString(j).toString();
			    			drtn[i][k]=n;
			    			System.out.println(drtn[i][k]);
			    			k++;
			    			j++;
			    		}while(cur2.getString(j)!=null);
			    		i++;
			       }
			       
			       dr[0]=drtn[0][0];
			        r=1;
			        k=0;
			       
				    for(i=1,j=0;drtn[i][j]!=null;i++){//removing repeatition of junction points
				    	for(l=i-1,k=1;drtn[l][k]!=null;k++){
				    		if(drtn[l][k].equals(drtn[i][j])==true){
				    			
				    			dr[r]=drtn[l][k];
				    		//	System.out.println("same"+dr[r]);
				    			r++;
				    		}
				    		else if(drtn[l][k].equals(drtn[i][j])==false)
				    		{
				    			dr[r]=drtn[l][k];
				    	//		System.out.println("bb"+dr[r]);
				    			r++;
				    		}
			   	    	}
				    }
				    i--;
				    while(drtn[i][j]!=null){
				    	dr[r]=drtn[i][j];
				    	j++;
				    }
				    i=0;
				    while(dr[i]!=null){
				    System.out.println("dfinal"+dr[i]);
				    i++;
				    }*/
			    Intent intent = new Intent(getBaseContext(),NextDisplay1.class);
			       intent.putExtra("srt",srt);
			       intent.putExtra("drt",drt);
			       intent.putExtra("src",src);
			       intent.putExtra("des",des);
			       startActivity(intent);
		     		  
			       
		       db.close();
		        		}
      }
      });
		      
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
}
public boolean onKeyDown(int keyCode, KeyEvent event){
    if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent Act2Intent = new Intent(this,MainActivity.class); 
            Act2Intent.setFlags(Act2Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(Act2Intent);          
            finish();
            return true;
    }
    return false;
}
}
