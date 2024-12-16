package libraryshare.infra;

import libraryshare.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class BookHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Book>> {

    @Override
    public EntityModel<Book> process(EntityModel<Book> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/registerbook")
                .withRel("registerbook")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/changebookstatus"
                )
                .withRel("changebookstatus")
        );

        return model;
    }
}
