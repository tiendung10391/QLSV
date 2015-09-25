package com.qlsv.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.qlsv.R;

public class Fragment_trangchu extends Fragment {
	ListView list;
	String[] itemname ={
			"Trung thu sum vầy cùng ITPlus",
			"ITPlus Academy ký kết hợp tác tiếp nhận sinh viên thực tập với EcoIT",
			"Thành công vang dội từ Hội thảo hướng nghiệp và tuyển dụng cho EcoIT",
			"ITPlus sôi động cùng ngày hội SFD 2015",
			"Hồi hộp chờ đón ngày hội phần mềm tự do nguồn mở 2015",
			"Tuyển sinh khóa học Thiết kế và lập trình web PHP chuyên nghiệp"
		};
	String[] itemtitle ={
		"Lần đầu tiên được đón Tết trung thu với đại gia đình ITPlus",
		"ITPlus Academy ký kết hợp tác tiếp nhận sinh viên thực tập với EcoIT",
		"Ngày 22/9 vừa qua, ITPlus đã tổ chức Hội thảo hướng nghiệp và tuyển dụng cho Công ty Cổ phần EcoIT.",
		"Ngày hội Phần mềm Tự do Nguồn mở 2015 được tổ chức vô cùng hoành tráng với hơn 2000 lượt khách tham gia",
		"Câu lạc bộ Phần mềm tự do Nguồn mở Việt Nam VFOSSA phối hợp với các đơn vị CNTT tổ chức ngày hội Phần mềm Tự do Nguồn mở (Software Free Day)",
		"ITPlus thông báo Khai giảng khóa học thiết kế và lập trình web PHP chuyên nghiệp: 22/9/2015, xem chi tiết..."
	};
	
	Integer[] imgid={
			R.drawable.chungthu,
			R.drawable.hoptac,
			R.drawable.huongnghiep,
			R.drawable.hoihop,
			R.drawable.hoiphanmem,
			R.drawable.tuyensinh
	};
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trang_chu, container,false);
        CustomListAdapter adapter = new CustomListAdapter(getActivity(), itemname, imgid, itemtitle);
        list = (ListView) view.findViewById(R.id.lvTintuc);
        list.setAdapter(adapter);
		return view;
        
    }
}
