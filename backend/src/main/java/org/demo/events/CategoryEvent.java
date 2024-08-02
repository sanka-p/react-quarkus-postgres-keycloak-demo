package org.demo.events;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.demo.dto.CategoryDTO;
import org.demo.dto.UserDTO;
import org.demo.entity.Category;
import org.demo.repository.CategoryRepository;
import org.demo.response.ErrorResponse;
import org.demo.response.Response;
import org.demo.response.SuccessResponse;
import org.hibernate.reactive.mutiny.Mutiny;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CategoryEvent {
    @Inject
    Mutiny.SessionFactory sessionFactory;

    @Inject
    CategoryRepository categoryRepository;

    @ConsumeEvent("get-all-items")
    public Uni<Response<?>> getAllItems () {
        return sessionFactory.withSession(session ->
                categoryRepository.listAll()
                        .onItem().transform(items -> {
                            List<CategoryDTO> categoryDTOS = new ArrayList<>();

                            for(Category category: items){
                                CategoryDTO categoryDTO = new CategoryDTO();
                                categoryDTO.setId(category.getId());
                                categoryDTO.setName(category.getName());
                                categoryDTOS.add(categoryDTO);
                            }

                            Response<List<CategoryDTO>> response = new SuccessResponse<>();
                            response.setStatusCode(RestResponse.StatusCode.OK);
                            response.setBody(categoryDTOS);
                            return response;
                        })
                        .onFailure().recoverWithItem(throwable -> {
                            Response<String> response = new ErrorResponse();
                            response.setStatusCode(RestResponse.StatusCode.INTERNAL_SERVER_ERROR);
                            response.setBody("Something went wrong");
                            return response;
                        })
        );


    }
}
