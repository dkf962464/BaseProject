package com.rocky.newringtones.vview.view.inter;

public interface IHomeAView {
    //������
    int REQUEST_ONE = 0;
    int REQUEST_TWO = 1;
    int REQUEST_THREE = 2;
    //��Ӧ���
    int RESPONSE_ONE = 0;
    int RESPONSE_TWO = 1;
    int RESPONSE_THREE = 2;

    <T> T request(int requestFlag);

    <T> void response(T response, int responseFlag);
}
