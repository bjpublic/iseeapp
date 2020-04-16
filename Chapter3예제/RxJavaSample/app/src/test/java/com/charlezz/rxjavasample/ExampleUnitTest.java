package com.charlezz.rxjavasample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.reactivestreams.Publisher;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;
import io.reactivex.rxjava3.subjects.UnicastSubject;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testCreate() {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("Hello");
            emitter.onNext("World");
            emitter.onComplete();
        });
        source.subscribe(System.out::println);
    }

    @Test
    public void testCreate2() {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("Hello");
            emitter.onComplete();
            emitter.onNext("World");
        });
        source.subscribe(System.out::println);
    }

    @Test
    public void testCreateWithError() {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("Hello");
            emitter.onError(new Throwable());
            emitter.onNext("World");
        });

        source.subscribe(System.out::println, throwable -> System.out.println("Error!!"));
    }

    @Test
    public void testJust() {
        Observable<String> source = Observable.just("Hello", "World");
        source.subscribe(System.out::println);
    }

    @Test
    public void testFromArray() {
        String[] itemArray = new String[]{"A", "B", "C"};
        Observable source = Observable.fromArray(itemArray);
        source.subscribe(System.out::println);
    }

    @Test
    public void testFromIterable() {
        ArrayList itemList = new ArrayList();
        itemList.add("A");
        itemList.add("B");
        itemList.add("C");
        Observable source = Observable.fromIterable(itemList);
        source.subscribe(System.out::println);
    }

    @Test
    public void testFromFuture() {
        Future<String> future = Executors.newSingleThreadExecutor()
                .submit(() -> {
                    Thread.sleep(5000);
                    return "Hello World";
                });
        Observable source = Observable.fromFuture(future);
        source.subscribe(System.out::println); //블로킹되어 기다림
    }

    @Test
    public void testFromPublisher() {
        Publisher<String> publisher = subscriber -> {
            subscriber.onNext("A");
            subscriber.onNext("B");
            subscriber.onNext("C");
            subscriber.onComplete();
        };

        Observable<String> source = Observable.fromPublisher(publisher);
        source.subscribe(System.out::println);
    }

    @Test
    public void testFromCallable() {
        Callable<String> callable = () -> "Hello World";
        Observable source = Observable.fromCallable(callable);
        source.subscribe(System.out::println);
    }

    @Test
    public void testSingle() {
        Single.just("Hello World")
                .subscribe(System.out::println);
    }

    @Test
    public void testSingle2() {
        Single.create(emitter -> emitter.onSuccess("Hello"))
                .subscribe(System.out::println);
    }

    @Test
    public void testTransforming() {
        Observable<Integer> src = Observable.just(1, 2, 3);
        Single<Boolean> singleSrc1 = src.all(i -> i > 0);
        Single<Integer> singleSrc2 = src.first(-1);
        Single<List<Integer>> singleSrc3 = src.toList();
        Single<String> singleSrc = Single.just("Hello World");
        Observable<String> observableSrc = singleSrc.toObservable();
    }

    @Test
    public void testMaybe() {
        Maybe.create(emitter -> {
            emitter.onSuccess(100);
            emitter.onComplete();//무시됨
        })
                .doOnSuccess(item -> System.out.println("doOnSuccess1"))
                .doOnComplete(() -> System.out.println("doOnComplete1"))
                .subscribe(System.out::println);
        Maybe.create(emitter -> emitter.onComplete())
                .doOnSuccess(item -> System.out.println("doOnSuccess2"))
                .doOnComplete(() -> System.out.println("doOnComplete2"))
                .subscribe(System.out::println);
    }

    @Test
    public void testObservableToMaybe() {
        Observable<Integer> src1 = Observable.just(1, 2, 3);
        Maybe<Integer> srcMaybe1 = src1.firstElement();
        srcMaybe1.subscribe(System.out::println);
        Observable<Integer> src2 = Observable.empty();
        Maybe<Integer> srcMaybe2 = src2.firstElement();
        srcMaybe2.subscribe(System.out::println, throwable -> {
        }, () -> System.out.println("onComplete!"));
    }

    @Test
    public void testCompletable() {
        Completable.create(emitter -> {
            //do something here
            emitter.onComplete();
        }).subscribe(() -> System.out.println("completed1"));

        Completable.fromRunnable(() -> {
            //do something here
        }).subscribe(() -> System.out.println("completed2"));
    }

    @Test
    public void testColdAndHot() throws InterruptedException {
        Observable src = Observable.interval(1, TimeUnit.SECONDS);
        src.subscribe(value -> System.out.println("#1: " + value));
        Thread.sleep(3000);
        src.subscribe(value -> System.out.println("#2: " + value));
        Thread.sleep(3000);
    }

    @Test
    public void testPublishAndConnect() throws InterruptedException {
        ConnectableObservable src = Observable.interval(1, TimeUnit.SECONDS)
                .publish();
        src.connect();
        src.subscribe(value -> System.out.println("#1: " + value));
        Thread.sleep(3000);
        src.subscribe(value -> System.out.println("#2: " + value));
        Thread.sleep(3000);
    }

    @Test
    public void testAutoConnect() throws InterruptedException {
        Observable<Long> src = Observable.interval(100, TimeUnit.MILLISECONDS)
                .publish()
                .autoConnect(2);
        src.subscribe(i -> System.out.println("A: " + i));
        src.subscribe(i -> System.out.println("B: " + i));
        Thread.sleep(500);
    }

    @Test
    public void testDisposable() {
        Observable source = Observable.just("A", "B", "C");
        Disposable disposable = source.subscribe(o ->
                System.out.println(source)
        );
    }

    @Test
    public void testDisposable2() throws InterruptedException {
        Observable source = Observable.interval(1000, TimeUnit.MILLISECONDS);// 1초에 한 번씩 아이템을 발행
        Disposable disposable = source.subscribe(System.out::println);
        new Thread(() -> {
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            disposable.dispose();
        }).start();

        Thread.sleep(4000);
    }

    @Test
    public void testCompositeDisposable() {
        Observable source = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Disposable d1 = source.subscribe(System.out::println);
        Disposable d2 = source.subscribe(System.out::println);
        Disposable d3 = source.subscribe(System.out::println);
        CompositeDisposable cd = new CompositeDisposable();
        cd.add(d1);
        cd.add(d2);
        cd.add(d3);
        // 또는
        cd.addAll(d1, d2, d3);
        // 특정 시점에 폐기하기
        cd.dispose();
    }

    @Test
    public void testDefer() {
        Observable<Long> justSrc = Observable.just(
                System.currentTimeMillis()
        );
        Observable<Long> deferSrc = Observable.defer(() ->
                Observable.just(System.currentTimeMillis())
        );
        System.out.println("#1 now = " + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("#2 now = " + System.currentTimeMillis());
        justSrc.subscribe(time -> System.out.println("#1 time = " + time));
        deferSrc.subscribe(time -> System.out.println("#2 time = " + time));
    }

    @Test
    public void testEmptyAndNever() {
        Observable.empty()
                .doOnTerminate(() -> System.out.println("empty 종료"))
                .subscribe(System.out::println);
        Observable.never()
                .doOnTerminate(() -> System.out.println("never 종료"))
                .subscribe(System.out::println);
    }

    @Test
    public void testInterval() throws InterruptedException {
        Disposable d = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);
        Thread.sleep(5000);
        d.dispose();
    }

    @Test
    public void testRange() {
        Observable.range(1, 3).subscribe(System.out::println);
    }

    @Test
    public void testTimer() throws InterruptedException {
        Observable src = Observable.timer(1, TimeUnit.SECONDS);
        System.out.println("구독!"); //구독하면 1초 뒤에 아이템이 발행된다.
        src.subscribe(event -> System.out.println("실행!"));
        Thread.sleep(1001);
    }

    @Test
    public void testMap() {
        Observable<Integer> intSrc = Observable.just(1, 2, 3);
        Observable<Integer> strSrc = intSrc.map(value -> value * 10);
        strSrc.subscribe(System.out::println);
    }

    @Test
    public void testFlatMap() {
        Observable<String> src = Observable.just("a", "b", "c");
        src.flatMap(str -> Observable.just(str + 1, str + 2))
                .subscribe(System.out::println);
    }

    @Test
    public void testFlatMap2() {
        Observable.range(2, 8)
                .flatMap(x -> Observable.range(1, 9)
                        .map(y -> String.format("%d*%d=%d", x, y, x * y)))
                .subscribe(System.out::println);
    }

    @Test
    public void testBuffer() {
        Observable.range(0, 10)
                .buffer(3).subscribe(integers -> {
            System.out.println("버퍼 데이터 발행");
            for (Integer integer : integers) {
                System.out.println("#" + integer);
            }
        });
    }

    @Test
    public void testScan() {
        Observable.range(1, 5)
                .scan((x, y) -> {
                    System.out.print(String.format("%d+%d=", x, y));
                    return x + y;
                })
                .subscribe(System.out::println);
    }

    @Test
    public void testScan2() {
        Observable.just("a", "b", "c", "d", "e")
                .scan((x, y) -> x + y)
                .subscribe(System.out::println);
    }

    @Test
    public void testGroupBy() {
        Observable.just("Magenta Circle",
                "Cyan Circle",
                "Yellow Triangle",
                "Yellow Circle", "Magenta Triangle", "Cyan Triangle")
                .groupBy(item -> {
                    if (item.contains("Circle")) {
                        return "C";
                    } else if (item.contains("Triangle")) {
                        return "T";
                    } else {
                        return "None";
                    }
                })
                .subscribe(group -> {
                    System.out.println(group.getKey() + "그룹 발행 시작");
                    group.subscribe(shape -> {
                        System.out.println(group.getKey() + ":" + shape);
                    });
                });
    }

    @Test
    public void testDebounce() throws InterruptedException {
        Observable.create(emitter -> {
            emitter.onNext("1");
            Thread.sleep(100);
            emitter.onNext("2");
            emitter.onNext("3");
            emitter.onNext("4");
            emitter.onNext("5");
            Thread.sleep(100);
            emitter.onNext("6");
        })
                .debounce(10, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);
        Thread.sleep(300);
    }

    @Test
    public void testDistinct() {
        Observable.just(1, 2, 2, 1, 3).distinct()
                .subscribe(System.out::println);
    }

    @Test
    public void testElementAt() {
        Observable.just(1, 2, 3, 4)
                .elementAt(2)
                .subscribe(System.out::println);
    }

    @Test
    public void testFilter() {
        Observable.just(2, 30, 22, 5, 60, 1)
                .filter(x -> x > 10)
                .subscribe(System.out::println);
    }

    @Test
    public void testSample() throws InterruptedException {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .sample(300, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);
        Thread.sleep(1000);
    }

    @Test
    public void testSkip() {
        Observable.just(1, 2, 3, 4)
                .skip(2)
                .subscribe(System.out::println);
    }

    @Test
    public void testTake() {
        Observable.just(1, 2, 3, 4)
                .take(2)
                .subscribe(System.out::println);
    }

    @Test
    public void testAll() {
        Observable.just(2, 1)
                .all(integer -> integer > 0)
                .subscribe(System.out::println);
    }

    @Test
    public void testAll2() {
        Observable.just(2, 1, 0)
                .all(integer -> integer > 0)
                .subscribe(System.out::println);
    }

    @Test
    public void testAmb() {
        ArrayList<Observable<Integer>> list = new ArrayList<>();
        list.add(Observable.just(20, 40, 60)
                .delay(100, TimeUnit.MILLISECONDS));
        list.add(Observable.just(1, 2, 3));
        list.add(Observable.just(0, 0, 0)
                .delay(200, TimeUnit.MILLISECONDS));
        Observable.amb(list).subscribe(System.out::println);
    }

    @Test
    public void testCombineLatest() throws InterruptedException {
        Observable<Integer> src1 = Observable.create(emitter -> {
            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    emitter.onNext(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

        Observable<String> src2 = Observable.create(emitter -> {
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                    emitter.onNext("A");
                    Thread.sleep(700);
                    emitter.onNext("B");
                    Thread.sleep(100);
                    emitter.onNext("C");
                    Thread.sleep(700);
                    emitter.onNext("D");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        });

        Observable.combineLatest(src1, src2, (num, str) -> num + str)
                .subscribe(System.out::println);
        Thread.sleep(5000);
    }

    @Test
    public void testZip() throws InterruptedException {
        Observable<Integer> src1 = Observable.create(emitter -> {
            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    emitter.onNext(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

        Observable<String> src2 = Observable.create(emitter -> {
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                    emitter.onNext("A");
                    Thread.sleep(700);
                    emitter.onNext("B");
                    Thread.sleep(100);
                    emitter.onNext("C");
                    Thread.sleep(700);
                    emitter.onNext("D");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
        Observable.zip(src1, src2, (num, str) -> num + str).subscribe(System.out::println);
        Thread.sleep(5000);
    }

    @Test
    public void testMerge() throws InterruptedException {
        Observable src1 = Observable.intervalRange(
                1,//시작값
                5,//발행 횟수
                0,//초기 지연
                100,//발행 간격
                TimeUnit.MILLISECONDS
        ).map(value -> value * 20);

        Observable src2 = Observable.create(emitter -> new Thread(() -> {
            try {
                Thread.sleep(350);
                emitter.onNext(1);
                Thread.sleep(200);
                emitter.onNext(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
        Observable.merge(src1, src2)
                .subscribe(System.out::println);
        Thread.sleep(1000);
    }

    @Test
    public void testNumberFormatException() {
        Observable.just("1", "2", "a", "3")
                .map(i -> Integer.parseInt(i))
                .subscribe(System.out::println);
    }

    @Test
    public void testErrorHandling() {
        Observable.just("1", "2", "a", "3")
                .map(i -> Integer.parseInt(i))
                .subscribe(System.out::println,
                        throwable -> System.out.println("Error!")
                );
    }

    @Test
    public void testOnErrorReturn() {
        Observable.just("1", "2", "a", "3")
                .map(i -> Integer.parseInt(i))
                .onErrorReturn(throwable -> -1)
                .subscribe(System.out::println);
    }

    @Test
    public void testOnErrorResumeNext() {
        Observable.just("1", "2", "a", "3")
                .map(i -> Integer.parseInt(i))
                .onErrorResumeNext(throwable -> Observable.just(100, 200, 300))
                .subscribe(System.out::println);
    }

    @Test
    public void testRetry() {
        Observable.just("1", "2", "a", "3")
                .map(i -> Integer.parseInt(i))
//                .retry()
                .retry(2)
                .subscribe(System.out::println);
    }

    @Test
    public void testDoOnEach() {
        Observable.just(1, 2, 3)
                .doOnEach(notification -> {
                    Integer i = notification.getValue();
                    boolean isOnNext = notification.isOnNext();
                    boolean isOnComplete = notification.isOnComplete();
                    boolean isOnError = notification.isOnError();
                    Throwable throwable = notification.getError();
                    System.out.println("i = " + i);
                    System.out.println("isOnNext = " + isOnNext);
                    System.out.println("isOnComplete = " + isOnComplete);
                    System.out.println("isOnError = " + isOnError);
                    if (throwable != null) {
                        throwable.printStackTrace();
                    }
                })
                .subscribe(value -> System.out.println("Subscribed = " + value));
    }

    @Test
    public void testDoOnNext() {
        Observable.just(1, 2, 3)
                .doOnNext(item -> {
                    if (item > 1) {
                        throw new IllegalArgumentException();
                    }
                })
                .subscribe(System.out::println, throwable -> throwable.printStackTrace());
    }

    @Test
    public void testDoOnSubscribe() {
        Observable.just(1, 2, 3)
                .doOnSubscribe(disposable -> System.out.println("구독 시작!"))
                .subscribe(System.out::println);
    }

    @Test
    public void testDoOnComplete() {
        Observable.just(1, 2, 3)
                .doOnComplete(() -> System.out.println("완료!!"))
                .subscribe(System.out::println);
    }

    @Test
    public void testDoOnError() {
        Observable.just(2, 1, 0)
                .map(i -> 10 / i)
                .doOnError(throwable -> System.out.println("오류!!"))
                .subscribe(System.out::println, t -> t.printStackTrace());
    }

    @Test
    public void testDoOnTerminate() {
        Observable.just(2, 1, 0)
                .map(i -> 10 / i)
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .subscribe(System.out::println, t -> t.printStackTrace());
    }

    @Test
    public void testDoOnDispose() throws InterruptedException {
        Observable src = Observable.interval(500, TimeUnit.MILLISECONDS)
                .doOnDispose(() -> System.out.println("doOnDispose"));
        Disposable disposable = src.subscribe(System.out::println);

        Thread.sleep(1100);
        disposable.dispose();
    }

    @Test
    public void testDoFinally() throws InterruptedException {
        Observable src = Observable.interval(500, TimeUnit.MILLISECONDS).doOnComplete(() -> System.out.println("doOnComplete")).doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doFinally(() -> System.out.println("doFinally"));
        Disposable disposable = src.subscribe(System.out::println);
        Thread.sleep(1100);
        disposable.dispose();
    }

    @Test
    public void testSubscribeOnAndObserveOn() {
        Observable<Integer> src = Observable.create(emitter -> {
            for (int i = 0; i < 3; i++) {
                String threadName = Thread.currentThread().getName();
                System.out.println("#Subs on " + threadName + ": " + i);
                emitter.onNext(i);
                Thread.sleep(100);
            }
            emitter.onComplete();
        });
        src.subscribe(s -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("#Obsv on " + threadName + ": " + s);
        });
    }

    @Test
    public void testSubscribeOnAndObserveOn2() throws InterruptedException {
        Observable<Integer> src = Observable.create(emitter -> {
            for (int i = 0; i < 3; i++) {
                String threadName = Thread.currentThread().getName();
                System.out.println("#Subs on " + threadName + ": " + i);
                emitter.onNext(i);
                Thread.sleep(100);
            }
            emitter.onComplete();
        });
        src.subscribeOn(Schedulers.io())
                .subscribe(s -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("#Obsv on " + threadName + ": " + s);
                });
        Thread.sleep(500);
    }

    @Test
    public void testSubscribeOnAndObserveOn3() throws InterruptedException {
        Observable<Integer> src = Observable.create(emitter -> {
            for (int i = 0; i < 3; i++) {
                String threadName = Thread.currentThread().getName();
                System.out.println("#Subs on " + threadName + ": " + i);
                emitter.onNext(i);
                Thread.sleep(100);
            }
            emitter.onComplete();
        });
        src.observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribe(s -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("#Obsv on " + threadName + ": " + s);
                });
        Thread.sleep(500);
    }

    @Test
    public void testSubscribeOnAndObserveOn4() throws InterruptedException {
        Observable.interval(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(value -> System.out.println(Thread.currentThread().getName() + ":" + value));
        Thread.sleep(1000);
    }

    @Test
    public void testBackPressure() throws InterruptedException {
        Observable.range(1, Integer.MAX_VALUE)
                .map(item -> {
                    System.out.println("아이템 발행: " + item);
                    return item;
                }).subscribe(item -> {
            Thread.sleep(100);
            System.out.println("아이템 소비: " + item);
        });
        Thread.sleep(30 * 1000);
    }

    @Test
    public void testBackPressure2() throws InterruptedException {
        Observable.range(1, Integer.MAX_VALUE)
                .map(item -> {
                    System.out.println("아이템 발행: " + item);
                    return item;
                })
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    Thread.sleep(100);
                    System.out.println("아이템 소비: " + item);
                });
        Thread.sleep(30 * 1000);
    }

    @Test
    public void testFlowable() throws InterruptedException {
        Flowable.range(1, Integer.MAX_VALUE)
                .map(item -> {
                    System.out.println("아이템 발행: " + item);
                    return item;
                })
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    Thread.sleep(100);
                    System.out.println("아이템 소비: " + item);
                });
        Thread.sleep(30 * 1000);
    }

    @Test
    public void testFlowable2() throws InterruptedException {
        Flowable.interval(10, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .map(item -> {
                    Thread.sleep(2000);
                    System.out.println("아이템 발행: " + item);
                    return item;
                })
                .subscribe(item -> {
                    System.out.println("아이템 소비: " + item);
                }, throwable -> throwable.printStackTrace());
        Thread.sleep(30 * 1000);
    }

    @Test
    public void testOnBackpressureBuffer() throws InterruptedException {
        Flowable.interval(10, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer()
                .observeOn(Schedulers.io())
                .map(item -> {
                    Thread.sleep(2000);
                    System.out.println("아이템 발행: " + item);
                    return item;
                })
                .subscribe(item -> {
                    System.out.println("아이템 소비: " + item);
                }, throwable -> throwable.printStackTrace());
        Thread.sleep(30 * 1000);
    }

    @Test
    public void testOnBackpressureLatest() throws InterruptedException {
        Flowable.interval(10, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    Thread.sleep(100);
                    System.out.println("아이템 소비: " + item);

                }, throwable -> throwable.printStackTrace());
        Thread.sleep(30 * 1000);
    }

    @Test
    public void testOnBackpressureDrop() throws InterruptedException {
        Flowable.range(1, 300)
                .onBackpressureDrop()
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    Thread.sleep(10);
                    System.out.println("아이템 소비: " + item);
                }, throwable -> throwable.printStackTrace());
        Thread.sleep(30 * 1000);
    }

    @Test
    public void testOnBackpressureDrop2() throws InterruptedException {
        Flowable.interval(10, TimeUnit.MILLISECONDS)
                .onBackpressureDrop(item -> {
                    System.out.println("아이템 버림: " + item);
                }).observeOn(Schedulers.io())
                .subscribe(item -> {
                    Thread.sleep(100);
                    System.out.println("아이템 소비: " + item);
                }, throwable -> throwable.printStackTrace());
        Thread.sleep(30 * 1000);
    }

    @Test
    public void testBackpressureStrategy() throws InterruptedException {
        Flowable.create((FlowableOnSubscribe<Integer>) emitter -> {
            for (int i = 0; i <= 1000; i++) {
                if (emitter.isCancelled()) { //다운스트림 취소 및 폐기 시 true
                    return;
                }
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER) //배압 제어 전략
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io()).subscribe(System.out::println,
                throwable -> throwable.printStackTrace());
        Thread.sleep(100);
    }

    @Test
    public void testPublishSubject() {
        Subject<String> src = PublishSubject.create();
        src.subscribe(item -> System.out.println("A: " + item), t -> t.printStackTrace(), () -> System.out.println("A: onComplete"));
        src.subscribe(item -> System.out.println("B: " + item), t -> t.printStackTrace(), () -> System.out.println("B: onComplete"));
        src.onNext("Hello");
        src.onNext("World");
        src.onNext("!!!");
        src.onComplete();
    }

    @Test
    public void testPublishSubject2() {
        Subject<String> src = PublishSubject.create();
        src.onNext("Hello");
        src.onNext("World");
        src.onNext("!!!");
        src.onComplete();
        src.map(String::length).subscribe(System.out::println);
    }

    @Test
    public void testPublishSubject3() throws InterruptedException {
        Observable src1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable src2 = Observable.interval(500, TimeUnit.MILLISECONDS);
        PublishSubject subject = PublishSubject.create();
        src1.map(item -> "A: " + item).subscribe(subject);
        src2.map(item -> "B: " + item).subscribe(subject);
        subject.subscribe(System.out::println);
        Thread.sleep(5000);
    }

    @Test
    public void testSerializedSubject() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        Subject<Object> subject = PublishSubject.create();
        subject.doOnNext(i -> counter.incrementAndGet())
                .doOnNext(i -> counter.decrementAndGet())
                .filter(i -> counter.get() != 0)
                .subscribe(System.out::println, throwable -> throwable.printStackTrace());
        Runnable runnable = () -> {
            for (int i = 0; i < 100000; i++) {
                try {
                    Thread.sleep(1);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                subject.onNext(i);
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        Thread.sleep(1000);
        System.out.println("종료");
    }

    @Test
    public void testSerializedSubject2() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        Subject<Object> subject = PublishSubject.create().toSerialized();
        subject.doOnNext(i -> counter.incrementAndGet())
                .doOnNext(i -> counter.decrementAndGet())
                .filter(i -> counter.get() != 0)
                .subscribe(System.out::println, throwable -> throwable.printStackTrace());

        Runnable runnable = () -> {

            for (int i = 0; i < 100000; i++) {
                try {
                    Thread.sleep(1);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                subject.onNext(i);
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        Thread.sleep(1000);
        System.out.println("종료");
    }

    @Test
    public void testBehaviorSubject() {
        BehaviorSubject<Integer> subject = BehaviorSubject.create();
        subject.subscribe(item -> System.out.println("A: " + item));
        subject.onNext(1);
        subject.onNext(2);
        subject.subscribe(item -> System.out.println("B: " + item));
        subject.onNext(3);
        subject.subscribe(item -> System.out.println("C: " + item));
    }

    @Test
    public void testReplaySubject() {
        ReplaySubject<Integer> subject = ReplaySubject.create();
        subject.subscribe(item -> System.out.println("A: " + item));
        subject.onNext(1);
        subject.onNext(2);
        subject.subscribe(item -> System.out.println("B: " + item));
        subject.onNext(3);
        subject.subscribe(item -> System.out.println("C: " + item));
    }

    @Test
    public void testAsyncSubject() {
        AsyncSubject<Integer> subject = AsyncSubject.create();
        subject.subscribe(item -> System.out.println("A: " + item));
        subject.onNext(1);
        subject.onNext(2);
        subject.subscribe(item -> System.out.println("B: " + item));
        subject.onNext(3);
        subject.onComplete();
        subject.subscribe(item -> System.out.println("C: " + item));
    }

    @Test
    public void testUnicastSubject() throws InterruptedException {
        Subject<Long> subject = UnicastSubject.create();
        Observable.interval(1, TimeUnit.SECONDS).subscribe(subject);
        Thread.sleep(3000);
        subject.subscribe(i -> System.out.println("A: " + i));
        Thread.sleep(2000);
    }
}