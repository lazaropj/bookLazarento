package br.com.linkout.bookLazarento.pages.livros;

import jmine.tec.di.annotation.Injected;
import jmine.tec.web.wicket.pages.Template;

import org.apache.wicket.Session;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import bancosys.tec.exception.MessageCreator;
import bancosys.tec.exception.MessageCreatorHelper;
import br.com.linkout.bookLazarento.dao.LivroDAO;
import br.com.linkout.bookLazarento.domain.Livro;

public class NovoLivroPage extends Template {
	
	private static final long serialVersionUID = -6537143015291853696L;
	
	@Injected
	private LivroDAO livroDAO;
	
	@SuppressWarnings("rawtypes")
	private Form form;	
	private FeedbackPanel feedbackPanel;
    private RequiredTextField<String> inputTitulo;
    private RequiredTextField<Double> inputValorDaLocacao;
    private Livro novoLivro;
    
    @SuppressWarnings("serial")
	public NovoLivroPage() {
    	// Criando a nova entidade para armazenar os valores preenchidos
        // na tela
        novoLivro = new Livro();

        // Criando um formulário com o mesmo wicket:id que o do HTML, indicando
        // que os campos do formulário devem ser associados aos atributos da instância
        // apontada por novoLivro
        form = new Form<Livro>("novoLivroForm", new CompoundPropertyModel<Livro>(novoLivro));

        // Definindo os campos do formulário como tipo texto e de preenchimento
        // obrigatório - a ser validado automaticamente
        inputTitulo = new RequiredTextField<String>("titulo");
        inputValorDaLocacao = new RequiredTextField<Double>("valorDaLocacao",Double.class);
        form.add(inputTitulo);
        form.add(inputValorDaLocacao);

        // Definindo o botão de criação de livro
        Button criarLivro = new Button("criarLivro") {

               // Define o comportamento a ser realizado no clique do botão
               public void onSubmit() {
                      livroDAO.save(novoLivro);

                      // Tudo certo até aqui! Enviando uma mensagem de sucesso...
                      FeedbackMessage mensagemDeSucesso = new FeedbackMessage(form,
                                   "Livro criado com sucesso!",
                                    FeedbackMessage.INFO);
                      Session.get().getFeedbackMessages().add(mensagemDeSucesso);
               }
        };
        form.add(criarLivro);

        // Criando um painel para mensagens de feedback
        feedbackPanel = new FeedbackPanel("feedback");
        form.add(feedbackPanel);
        add(form);
	}


    // Implementação obrigatória para o template do Jmine.
    // Define que mensagem deve estar disponível para o usuário
    // na área de HELP do template padrão
	@Override
	protected MessageCreator getHelpTextCreator() {
		return MessageCreatorHelper.creator("livros-mensagens", "novo_livro.help", 0);
	}

}
