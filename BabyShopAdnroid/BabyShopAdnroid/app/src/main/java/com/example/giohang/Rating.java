package com.example.giohang;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;


public class Rating extends AppCompatActivity {
    ImageView btnBack, ratingImage;
    EditText txtRating;
    Button button_sendstar;

    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtRating = findViewById(R.id.txtRating);
        ratingImage = findViewById(R.id.ratingImage);
        button_sendstar = findViewById(R.id.button_sendstar);

        button_sendstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                String feedback = txtRating.getText().toString();

                // Kiểm tra nếu rating và feedback không rỗng thì mới gửi đánh giá
                if (rating > 0 && !feedback.isEmpty()) {
                    animateImage(ratingImage);

                    SubmitRatingTask submitRatingTask = new SubmitRatingTask(getApplicationContext());
                    submitRatingTask.execute(rating, feedback);
                    finish();
                } else {
                    // Hiển thị thông báo nếu rating hoặc feedback trống
                    Toast.makeText(Rating.this, "Vui lòng nhập đánh giá và phản hồi.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 1) {
                    ratingImage.setImageResource(R.drawable.one_star);
                } else if (rating <= 2) {
                    ratingImage.setImageResource(R.drawable.two_star);
                } else if (rating <= 3) {
                    ratingImage.setImageResource(R.drawable.three_star);
                } else if (rating <= 4) {
                    ratingImage.setImageResource(R.drawable.four_star);
                } else if (rating <= 5) {
                    ratingImage.setImageResource(R.drawable.five_star);
                }
            }
        });
    }

    private void animateImage(ImageView ratingImage) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.startAnimation(scaleAnimation);
    }
}
