package com.example.demo;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.schedulers.Schedulers;

public class RxTest {

	public static void main(String[] args) {

		Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				emitter.onNext("1");
				emitter.onNext("2");
				emitter.onNext("3");
				emitter.onComplete();
			}
		}).observeOn(NewThreadScheduler.instance())
				.subscribeOn(Schedulers.io())
				.subscribe(new Observer<String>() {
					Disposable mDisposable;

					@Override
					public void onComplete() {
						System.out.println("onComplete");
					}

					@Override
					public void onNext(String value) {
//						if ("212".equals(value)) {
//							mDisposable.dispose();
//							return;
//						}
						System.out.println("onNext: " + value);
					}

					@Override
					public void onError(Throwable e) {
						System.out.println("onError: " + e);
					}

					@Override
					public void onSubscribe(Disposable d) {
						mDisposable = d;
						System.out.println("onSubscribe");
					}
				});


	}

}
