package com.icandothisallday2020.ex52bottomnavigationview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    FragmentManager manager;
    Fragment[] fragments=new Fragment[4];//참조변수 4개

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fragment 의 동적 추가/삭제/제거 를 위한 관리자 객체 소환
        manager=getSupportFragmentManager();
        //각 탭화면 Fragment 객체를 생성
        fragments[0]=new Tab1Fragment();
        fragments[1]=new Tab2Fragment();
        fragments[2]=new Tab3Fragment();
        fragments[3]=new Tab4Fragment();
        //첫번재 탭 화면 붙이는 작업
        FragmentTransaction tran= manager.beginTransaction();
        tran.add(R.id.container, fragments[0]);
        tran.commit();

        bnv=findViewById(R.id.bottom_navi);
        bnv.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //Fragment 작업 Transaction 시작
                FragmentTransaction tran=manager.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.menu_search:
                        tran.replace(R.id.container,fragments[0]);
                        break;
                    case R.id.menu_gallery:
                        tran.replace(R.id.container,fragments[1]);
                        break;
                    case R.id.menu_camera:
                        tran.replace(R.id.container,fragments[2]);
                        break;
                    case  R.id.menu_info:
                        tran.replace(R.id.container,fragments[3]);
                        break;
                }
                //Transaction 작업 완료 명령
                tran.commit();
                return true;//선택효과적용
            }
        });
    }
}
