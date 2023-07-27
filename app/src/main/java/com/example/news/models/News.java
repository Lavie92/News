package com.example.news.models;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class News {
    private String title;
    private String url;
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public News() {
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public News(String title, String url, String imageUrl) {
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public News(String title, String url, String imageUrl, Bitmap bitmap) {
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.imageBitmap = bitmap;
    }


    public static List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        News newsDefault1 = new News(
                "Bị tình trẻ kém 45 tuổi trở mặt vì hết tiền, ông lão vác dao trả thù",
                        "https://vietnamnet.vn/bi-tinh-tre-kem-45-tuoi-tro-mat-vi-het-tien-ong-lao-vac-dao-tra-thu-2169396.html",
                        "https://static-images.vnncdn.net/files/publish/2023/7/25/bi-caon123-1086.jpeg");

        News newsDefault2 = new News(
                "Vụ cầm dao đâm hàng xóm chung cư Hà Nội: Cử bảo vệ trực, cho con cái về quê",
                "https://dantri.com.vn/doi-song/vu-cam-dao-dam-hang-xom-chung-cu-ha-noi-cu-bao-ve-truc-cho-con-cai-ve-que-20230724214129776.htm",
                "https://icdn.dantri.com.vn/2023/07/24/z45438851571430c1fab0ef0cab58ce330b055fd856ea0-1690208892071.jpg");
        News newsDefault3 = new News(
                "Thiếu giáo viên ngày càng trầm trọng, tiếp diễn tình trạng giáo viên nghỉ việc",
                "https://thanhnien.vn/thieu-giao-vien-ngay-cang-tram-trong-tiep-dien-tinh-trang-giao-vien-nghi-viec-185230721191333364.htm",
                "https://images2.thanhnien.vn/528068263637045248/2023/7/21/tieng-anh-meo-vac-2-16899415206352093461138.jpg");
        News newsDefault4 = new News(
                "Những hình ảnh gợi nhớ 'tuổi thơ dữ dội' của thế hệ 8x, 9x",
                "https://vtc.vn/nhung-hinh-anh-goi-nho-tuoi-tho-du-doi-cua-the-he-8x-9x-ar808618.html",
                "https://cdn-i.vtcnews.vn/upload/2023/07/26/chinh-mario-14402941.jpg");
        News newsDefault5 = new News(
                "Michelin Guide viết về sự khác nhau của phở Bắc và phở Nam",
                "https://tuoitre.vn/michelin-guide-viet-ve-su-khac-nhau-cua-pho-bac-va-pho-nam-20230727000922309.htm",
                "https://cdn.tuoitre.vn/471584752817336320/2023/7/26/3634958929957724584387426678339247650341518n-16903904590721116643650.png");
        newsList.add(newsDefault1);
        newsList.add(newsDefault2);
        newsList.add(newsDefault3);
        newsList.add(newsDefault4);
        newsList.add(newsDefault5);
        return newsList;
    }

    private Bitmap imageBitmap;

    public void setImageBitmap(Bitmap bitmap) {
        this.imageBitmap = bitmap;
    }
}
