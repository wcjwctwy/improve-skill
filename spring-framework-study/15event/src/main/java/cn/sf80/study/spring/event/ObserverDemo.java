package cn.sf80.study.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observer} 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Observer
 * @since
 */
public class ObserverDemo {

    public static void main(String[] args) {
        EventObservable observable = new EventObservable();
        // 添加观察者（监听者）
        observable.addObserver(new EventObserver());
        // 发布消息（事件）
        observable.notifyObservers("Hello,World");
    }

    static class EventObservable extends Observable {

        @Override
        public void setChanged() {
            super.setChanged();
        }
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println("收到事件 ：" + eventObject);
        }
    }
}