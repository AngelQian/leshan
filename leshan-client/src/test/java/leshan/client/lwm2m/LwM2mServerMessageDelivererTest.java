package leshan.client.lwm2m;

import static leshan.client.lwm2m.response.OperationResponseCode.NOT_FOUND;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import leshan.client.lwm2m.response.ResponseMatcher;

import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.OptionSet;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.core.observe.ObserveRelation;
import org.eclipse.californium.core.server.resources.Resource;
import org.eclipse.californium.core.server.resources.ResourceAttributes;
import org.eclipse.californium.core.server.resources.ResourceObserver;
import org.junit.Before;
import org.junit.Test;

public class LwM2mServerMessageDelivererTest {

	private LwM2mServerMessageDeliverer deliverer;
	private Exchange exchange;
	private Resource root;

	@Before
	public void setup() {
		root = new TestResource();
		deliverer = new LwM2mServerMessageDeliverer(root);
	}

	@Test
	public void canDeliverOneLevel() {
		final TestResource resource = spy(new TestResource("3"));
		root.add(resource);

		deliver(Code.GET, "3");
		verify(resource).handleRequest(exchange);
		verifyNoErrorMessage();
	}

	@Test
	public void canDeliverThreeLevels() {
		final Resource parent = new TestResource("3");
		final Resource child = new TestResource("4");
		final Resource grandchild = spy(new TestResource("5"));

		child.add(grandchild);
		parent.add(child);
		root.add(parent);

		deliver(Code.GET, "3", "4", "5");
		verify(grandchild).handleRequest(exchange);
		verifyNoErrorMessage();
	}

	@Test
	public void failedPostDeliveryThreeLevelsDeepGetsReported() {
		final Resource parent = new TestResource("3");
		final Resource child = new TestResource("4");

		parent.add(child);
		root.add(parent);

		deliver(Code.POST, "3", "4", "5");
		verifyErrorMessage();
	}

	@Test
	public void failedGetDeliveryTwoLevelsDeepGetsReported() {
		final Resource resource = spy(new TestResource("3"));

		root.add(resource);

		deliver(Code.GET, "3", "4");
		verifyErrorMessage();
		verify(resource, never()).handleRequest(any(Exchange.class));
	}

	@Test
	public void failedPostDeliveryTwoLevelsDeepGetsDeliveredToParent() {
		final Resource resource = spy(new TestResource("3"));

		root.add(resource);

		deliver(Code.POST, "3", "4");
		verify(resource).handleRequest(exchange);
		verifyNoErrorMessage();
	}

	private void deliver(final Code code, final String... uriPath) {
		final OptionSet options = new OptionSet();
		for (final String path : uriPath) {
			options.addURIPath(path);
		}
		final Request request = new Request(code);
		request.setOptions(options);
		exchange = mock(Exchange.class);
		when(exchange.getRequest()).thenReturn(request);

		deliverer.deliverRequest(exchange);
	}

	private void verifyErrorMessage() {
		verify(exchange).sendResponse(argThat(new ResponseMatcher(NOT_FOUND, null)));
	}

	private void verifyNoErrorMessage() {
		verify(exchange, never()).sendResponse(argThat(new ResponseMatcher(NOT_FOUND, null)));
	}

	private class TestResource implements Resource {

		private String name;
		private final Map<String, Resource> children = new HashMap<>();

		public TestResource() {
		}

		public TestResource(final String name) {
			this.name = name;
		}

		@Override
		public void handleRequest(final Exchange exchange) {
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public void setName(final String name) {
		}

		@Override
		public String getPath() {
			return null;
		}

		@Override
		public void setPath(final String path) {
		}

		@Override
		public String getURI() {
			return null;
		}

		@Override
		public boolean isVisible() {
			return false;
		}

		@Override
		public boolean isCachable() {
			return false;
		}

		@Override
		public boolean isObservable() {
			return false;
		}

		@Override
		public ResourceAttributes getAttributes() {
			return null;
		}

		@Override
		public void add(final Resource child) {
			children.put(child.getName(), child);
		}

		@Override
		public boolean remove(final Resource child) {
			return false;
		}

		@Override
		public Collection<Resource> getChildren() {
			return null;
		}

		@Override
		public Resource getChild(final String name) {
			return children.get(name);
		}

		@Override
		public Resource getParent() {
			return null;
		}

		@Override
		public void setParent(final Resource parent) {
		}

		@Override
		public void addObserver(final ResourceObserver observer) {
		}

		@Override
		public void removeObserver(final ResourceObserver observer) {
		}

		@Override
		public void addObserveRelation(final ObserveRelation relation) {
		}

		@Override
		public void removeObserveRelation(final ObserveRelation relation) {
		}

		@Override
		public Executor getExecutor() {
			return null;
		}

		@Override
		public List<Endpoint> getEndpoints() {
			return null;
		}

	}

}
