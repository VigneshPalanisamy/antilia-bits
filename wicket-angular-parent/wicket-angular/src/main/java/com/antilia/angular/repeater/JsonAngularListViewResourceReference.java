package com.antilia.angular.repeater;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;

import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.ResourceStreamResource;
import org.apache.wicket.util.resource.StringResourceStream;



/**
 * A resource that serves JSON for stateless . You have to mount at application init like. E.g.
 * 
 * <p>
 * <pre>
 * 	mountResource(CATEGORIES_JSON, new JsonAngularListViewResourceReference<CategoryTranslation>() {
 * 			
 * 			private static final long serialVersionUID = 1L;
 * 	
 * 			protected ChoiceProvider<CategoryTranslation> getChoiceProvider() {
 * 				return CategoriesTextChoiceProvider.getInstance();
 * 			}
 * 		});
 * 		
 * </pre>	
 * </p>
 * 
 * and then on component you add
 * 
 * <p>
 *  <pre>
 *  	Select2MultiChoice<CategoryTranslation> c = new Select2MultiChoice<CategoryTranslation>("categories", new PropertyModel<Collection<CategoryTranslation>>(searchBean, "translations"), CategoriesTextChoiceProvider.getInstance());
 *  	c.getSettings().setStateless(true);
 *  	c.getSettings().setMountPath(Application.CATEGORIES_JSON);
 *  </pre>
 * <p>
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@mail.com).
 */
public abstract class JsonAngularListViewResourceReference<T> extends ResourceReference {
	private static final long serialVersionUID = 1L;

	public JsonAngularListViewResourceReference() {
		super(JsonAngularListViewResourceReference.class, "images");
	}

	@Override
	public IResource getResource() {
	    ByteArrayOutputStream webResponse = new ByteArrayOutputStream();
	    AngularListView.generateJSON(getIjsoNifier(), getListIterator(), webResponse);
	    StringResourceStream resourceStream = new StringResourceStream(webResponse.toString(), "application/json");
	    return new ResourceStreamResource(resourceStream);
	}

	/**
	 * The choice provider.
	 * 
	 * @return
	 */
	protected abstract IJSONifier<T> getIjsoNifier();
	
	protected abstract Iterator<? extends T> getListIterator();

}
