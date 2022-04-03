package hello.core.singleton;

/**
 * 공유 필드의 사용은 좋지 않다.
 * 여러 클라이언트가 접근하고 수정할 수 있는 패턴을 큰 문제를 야기한다.
 * 공유 필드를 제거하자.
 */
public class StatefulService {

//    private int price; //상태를 유지하는 필드

//    public void order(String name, int price) {
    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
//        this.price = price; //여기가 문제!
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
