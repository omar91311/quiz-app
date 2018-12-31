package com.sabdar.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView ques_1,ques_2,ques_3,ques_4,ques_5;
    EditText answerOneEditText;
    RadioGroup answerTwoRadioGroup;
    CheckBox answerThreeCheckBoxOne;
    CheckBox answerThreeCheckBoxTwo;
    CheckBox answerThreeCheckBoxThree;
    CheckBox answerThreeCheckBoxFour;
    EditText answerFourEditText;
    RadioGroup answerFiveRadioGroup;

    String[] question={"Simplify 10 + 50 / 25 * 60 -20",
        "What is Square of 45 ?",
        "Find value of x in the expression x^2 +  3x + 1",
        "The Product of 25 and 20",
        "If x= 20 , y = 10 , then Value of x + 20y is"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerOneEditText = findViewById(R.id.text_answer_1);
        answerTwoRadioGroup = findViewById(R.id.radio_group_answer_2);
        answerThreeCheckBoxOne = findViewById(R.id.check_box_answer_3_a);
        answerThreeCheckBoxTwo = findViewById(R.id.check_box_answer_3_b);
        answerThreeCheckBoxThree = findViewById(R.id.check_box_answer_3_c);
        answerThreeCheckBoxFour = findViewById(R.id.check_box_answer_3_d);
        answerFourEditText = findViewById(R.id.text_answer_4);
        answerFiveRadioGroup = findViewById(R.id.radio_group_answer_5);

        ques_1=findViewById(R.id.text_question_1);
        ques_2=findViewById(R.id.radio_question_2);
        ques_3=findViewById(R.id.checkbox_question_3);
        ques_4=findViewById(R.id.text_question_4);
        ques_5=findViewById(R.id.radio_question_5);

        ques_1.setText(question[0]);
        ques_2.setText(question[1]);
        ques_3.setText(question[2]);
        ques_4.setText(question[3]);
        ques_5.setText(question[4]);




    }


    public void submitQuiz(View view) {
        int score = 0;
        int total = 5;

        String answer1 = answerOneEditText.getText().toString();
        if (answer1.equalsIgnoreCase(getString(R.string.answer_1))) {
            score += 1;
        }

        if (answerTwoRadioGroup.getCheckedRadioButtonId() == R.id.radio_answer_2_a) {
            score += 1;
        }

        if (answerThreeCheckBoxOne.isChecked() && answerThreeCheckBoxTwo.isChecked()&& !(answerThreeCheckBoxThree.isChecked())&&(!answerThreeCheckBoxFour.isChecked())) {
            score += 1;
        }

        String answer4 = answerFourEditText.getText().toString();
        if (answer4.equalsIgnoreCase(getString(R.string.answer_4))) {
            score += 1;
        }

        if (answerFiveRadioGroup.getCheckedRadioButtonId() == R.id.radio_answer_5_b) {
            score += 1;
        }


        Toast.makeText(this, getString(R.string.result_score_message, score, total), Toast.LENGTH_LONG).show();
        resetAnswers();
    }

    private void resetAnswers() {
        answerOneEditText.setText("");
        answerTwoRadioGroup.clearCheck();
        answerThreeCheckBoxOne.setChecked(false);
        answerThreeCheckBoxTwo.setChecked(false);
        answerThreeCheckBoxThree.setChecked(false);
        answerThreeCheckBoxFour.setChecked(false);
        answerFourEditText.setText("");
        answerFiveRadioGroup.clearCheck();
    }

}
