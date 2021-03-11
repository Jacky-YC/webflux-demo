package com.example.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProConLockTest {

	public static void main(String[] args) {

		Data2 data = new Data2();

		new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {
					data.increment();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "A").start();

		new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {
					data.decrement();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "B").start();

		new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {
					data.increment();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "C").start();

		new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {
					data.decrement();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "D").start();


	}


}


class Data2 {


	// 产品数量
	private int number = 0;

	private Lock lock = new ReentrantLock();

	private Condition condition = lock.newCondition();

	//+1
	public void increment() throws InterruptedException {
		lock.lock();
		try {
			while (number > 0) {
				// 等待
				condition.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName() + ": " + number);
			// 通知
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	//-1
	public void decrement() throws InterruptedException {
		lock.lock();
		try {
			while (number <= 0) {
				// 等待
				condition.await();
			}
			number--;
			System.out.println(Thread.currentThread().getName() + ": " + number);
			// 通知
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

}