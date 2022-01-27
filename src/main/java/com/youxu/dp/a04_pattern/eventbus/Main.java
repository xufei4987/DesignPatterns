package com.youxu.dp.a04_pattern.eventbus;

public class Main {
    public static void main(String[] args) {
        AbstractEventBus eventBus = new SyncEventBus();
        eventBus.register(new ObserverA());
        eventBus.register(new ObserverB());
        eventBus.post(10);
        eventBus.post("helloworld");
    }

    public static class ObserverA{
        @Subscribe
        public void call1(Integer i){
            System.out.println("ObserverA call1:" + i);
        }

        @Subscribe
        public void call2(Integer i){
            System.out.println("ObserverA call2:" + i);
        }

        @Subscribe
        public void call3(String a){
            System.out.println("ObserverA call3:" + a);
        }
    }

    public static class ObserverB{
        @Subscribe
        public void call1(String a){
            System.out.println("ObserverB call1:" + a);
        }

        @Subscribe
        public void call2(Integer i){
            System.out.println("ObserverB call2:" + i);
        }
    }
}
