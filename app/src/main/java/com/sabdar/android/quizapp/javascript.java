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

public class javascript extends AppCompatActivity {

    TextView ques_1,ques_2,ques_3,ques_4,ques_5;
    RadioGroup answerOneRadioGroup;
    RadioGroup answerTwoRadioGroup;
    RadioGroup answerThreeRadioGroup;
    RadioGroup answerFourRadioGroup;
    RadioGroup answerFiveRadioGroup;
	
	private DatabaseHelper dh;

    String[] question={"Which of the following is true about variable naming conventions in JavaScript?",
        "Which of the following is true about cookie handling in JavaScript?",
        "Which built-in method returns the character at the specified index?",
        "Which built-in method returns the calling string value converted to lower case?",
        "Which of the following function of Number object returns a string value version of the current number?"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.javascript);

        answerOneRadioGroup = findViewById(R.id.radio_group_answer_1);
        answerTwoRadioGroup = findViewById(R.id.radio_group_answer_2);
        answerThreeRadioGroup = findViewById(R.id.radio_group_answer_3);
        answerFourRadioGroup = findViewById(R.id.radio_group_answer_4);
        answerFiveRadioGroup = findViewById(R.id.radio_group_answer_5);
        
        ques_1=findViewById(R.id.radio_question_1);
        ques_2=findViewById(R.id.radio_question_2);
        ques_3=findViewById(R.id.radio_question_3);
        ques_4=findViewById(R.id.radio_question_4);
        ques_5=findViewById(R.id.radio_question_5);

        ques_1.setText(question[0]);
        ques_2.setText(question[1]);
        ques_3.setText(question[2]);
        ques_4.setText(question[3]);
        ques_5.setText(question[4]);

        dh = new DatabaseHelper(this);
    }


    public void submitQuiz(View view) {
        int score = 0;
        int total = 5;
	if (answerOneRadioGroup.getCheckedRadioButtonId() == R.id.radio_answer_1_a) {
            score += 1;
        }
		if (answerTwoRadioGroup.getCheckedRadioButtonId() == R.id.radio_answer_2_a) {
            score += 1;
        }
		if (answerThreeRadioGroup.getCheckedRadioButtonId() == R.id.radio_answer_3_a) {
            score += 1;
        }
		if (answerFourRadioGroup.getCheckedRadioButtonId() == R.id.radio_answer_4_a) {
            score += 1;
        }
		if (answerFiveRadioGroup.getCheckedRadioButtonId() == R.id.radio_answer_5_a) {
            score += 1;
        }
        Toast.makeText(this, getString(R.string.result_score_message, score, total), Toast.LENGTH_LONG).show();
        resetAnswers();
		String qw = dh.addResult("Javascript", score);
		Toast.makeText(this, qw, Toast.LENGTH_SHORT).show();
		
    }

    private void resetAnswers() {
        answerOneRadioGroup.clearCheck();
        answerTwoRadioGroup.clearCheck();
        answerThreeRadioGroup.clearCheck();
        answerFourRadioGroup.clearCheck();
        answerFiveRadioGroup.clearCheck();
    }
}