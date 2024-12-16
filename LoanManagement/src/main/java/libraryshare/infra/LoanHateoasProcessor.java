package libraryshare.infra;

import libraryshare.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class LoanHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Loan>> {

    @Override
    public EntityModel<Loan> process(EntityModel<Loan> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/createloan")
                .withRel("createloan")
        );

        return model;
    }
}
