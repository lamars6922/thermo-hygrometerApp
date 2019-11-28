# 원격 thermo-hygrometerApp

![11](https://user-images.githubusercontent.com/57030114/69795058-cad4b080-120e-11ea-9287-da1450a47bdf.PNG) ![22](https://user-images.githubusercontent.com/57030114/69795098-e2139e00-120e-11ea-8486-fc169e13ce84.PNG) ![33](https://user-images.githubusercontent.com/57030114/69795110-e8a21580-120e-11ea-97ab-61c09a133213.PNG)

------
# 개발 목적
* 예를 들어 비닐하우스, 와인창고 등과 같이 온도와 습도를 항상 일정하게 유지해야 하는 장소가 있으면 원격으로 온습도를 확인해야 할 경우가 있음.
* 한시간 마다 자동으로 온습도를 기록해야 할 때가 있음.
* 원하는 날짜나 시간의 온습도를 알아야할 때가 있음.

# 개발 목표
* 라즈베리파이를 이용하여, 온습도 센서를 제어한다.
* 온습도 센서가 온습도를 측정하면, 측정 값을 정해진 웹 서버에 출력한다.
* 웹 서버를 이용하여 한 시간마다 온습도 측정 값을 DB에 저장한다.
* DB에 저장된 모든 온습도 측정 값을 불러온다.
* 웹 서버와 JSON을 이용해서 모바일 앱으로 실시간 온습도 값을 받아 볼 수 있다.
* 모바일 앱에서 년원일을 입력하면, 해당 날짜에 온습도 값을 모두 읽어 모바일 앱에 출력해준다.

# 개발 환경
* 개발 툴 : Android Studio
* 개발 언어 : JAVA, Python
* 플랫폼 : Web Server, Android
* 프레임워크 : Django (os : RASPBIAN)
