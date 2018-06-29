package eclipse_examples.binding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.databinding.observable.Realm;

public class ExecutorRealm extends Realm {
	
	private final AtomicReference<Thread> executorThread = new AtomicReference<>(null);
	private final ExecutorService executor;

	public ExecutorRealm() {
		this.executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()) {
			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				executorThread.set(t);
				
				super.beforeExecute(t, r);
			}
		};
	}
	
	public ExecutorService getExecutor() {
		return executor;
	}

	@Override
	public boolean isCurrent() {
		return Thread.currentThread() == executorThread.get();
	}

	@Override
	public void asyncExec(Runnable runnable) {
		executor.submit(runnable);
	}

	@Override
	public void timerExec(int milliseconds, Runnable runnable) {
		throw new UnsupportedOperationException();
	}
}