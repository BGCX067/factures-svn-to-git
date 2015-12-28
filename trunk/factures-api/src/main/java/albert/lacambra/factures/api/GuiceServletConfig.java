package albert.lacambra.factures.api;

import albert.lacambra.factures.api.actions.FixCostActions;
import albert.lacambra.factures.api.actions.InvoiceActions;
import albert.lacambra.factures.api.actions.PersonAction;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceServletConfig extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		
		return Guice.createInjector(Stage.DEVELOPMENT, new JerseyServletModule() {

			@Override
			protected void configureServlets() {
//				bind(ResourceExceptionMapper.class).in(Singleton.class);
//				bind(FilterTest.class);
				bind(PersonAction.class);
				bind(InvoiceActions.class);
				bind(FixCostActions.class);
				this.bindResourcesActions();
				/*
				 *  Route all requests through GuiceContainer
				 */
				serve("/rest/*").with(GuiceContainer.class);
			}

			/**
			 * Resources' actions bindings
			 */
			private void bindResourcesActions(){
				
//				bind(NotConfirmedContactAction.class);
//				bind(NoteAction.class);
//				bind(UserAction.class);
//				bind(MessagesAction.class);
//				bind(PopulateAction.class);
//				bind(Logout.class);
			}
		}
//		, new Managers()
		);
	}
}





































































