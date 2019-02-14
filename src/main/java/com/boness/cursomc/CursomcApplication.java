package com.boness.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.boness.cursomc.domain.Categoria;
import com.boness.cursomc.domain.Cidade;
import com.boness.cursomc.domain.Cliente;
import com.boness.cursomc.domain.Endereco;
import com.boness.cursomc.domain.Estado;
import com.boness.cursomc.domain.ItemPedido;
import com.boness.cursomc.domain.Pagamento;
import com.boness.cursomc.domain.PagamentoComBoleto;
import com.boness.cursomc.domain.PagamentoComCartao;
import com.boness.cursomc.domain.Pedido;
import com.boness.cursomc.domain.Produto;
import com.boness.cursomc.domain.enums.EstadoPagamento;
import com.boness.cursomc.domain.enums.TipoCliente;
import com.boness.cursomc.repositories.CategoriaRepository;
import com.boness.cursomc.repositories.CidadeRepository;
import com.boness.cursomc.repositories.ClienteRepository;
import com.boness.cursomc.repositories.EnderecoRepository;
import com.boness.cursomc.repositories.EstadoRepository;
import com.boness.cursomc.repositories.ItemPedidoRepository;
import com.boness.cursomc.repositories.PagamentoRepository;
import com.boness.cursomc.repositories.PedidoRepository;
import com.boness.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner { // permite executar um método auxiliar que será executado na inicialização

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

@Override
public void run(String... args) throws Exception { // onde serão instanciados os objetos
	
	
	//Instanciando Categoria e Produtos
	Categoria cat1 = new Categoria(null, "Informatica");
	Categoria cat2 = new Categoria(null, "Escritório");
	
	Produto p1 = new Produto(null, "Computador", 2000.00);
	Produto p2 = new Produto(null, "Mesa Impressora", 200.00);
	Produto p3 = new Produto(null, "Mouse", 30.00);
	
	cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
	cat2.getProdutos().addAll(Arrays.asList(p2));
	
	p1.getCategorias().addAll(Arrays.asList(cat1));
	p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
	p3.getCategorias().addAll(Arrays.asList(cat1));
	
	categoriaRepository.save(Arrays.asList(cat1,cat2));
	produtoRepository.save(Arrays.asList(p1,p2,p3));
	
	// Instanciando Estados e Cidades
	Estado est1 = new Estado (null, "Bahia");
	Estado est2 = new Estado (null, "São Paulo");
	
	Cidade c1 = new Cidade(null,"Feira de Santana", est1);
	Cidade c2 = new Cidade(null, "São Paulo", est2);
	Cidade c3 = new Cidade(null, "Campinas", est2);
	
	est1.getCidades().addAll(Arrays.asList(c1)); 
	est2.getCidades().addAll(Arrays.asList(c2,c3));
	
	estadoRepository.save(Arrays.asList(est1,est2)); 
	cidadeRepository.save(Arrays.asList(c1,c2,c3)); 
	
	Cliente cli1= new Cliente (null, "maria Silva","maria@gmail.com", "12332112332", TipoCliente.PESSOAFISICA);
	cli1.getTelefones().addAll(Arrays.asList("998767890", "992233456"));
	Endereco e1 = new Endereco(null, "Rua a ","01","casa","Capuchinhos", "44000123",cli1, c1);
	Endereco e2 = new Endereco(null, "Rua b ","234",null,"Centro", "11000123",cli1, c2);

	// cliente tem que conhecer os seus enderecos
	cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
	
	// para salvar no banco temos que criar os repository (cliente e endereco)
	// fazer as declarações Autowired na criação dos objetos
	
	clienteRepository.save(Arrays.asList(cli1));
	enderecoRepository.save(Arrays.asList(e1,e2));

	//Instanciar (1)Pedido e (2)Pagamento
	
	SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy hh:mm");
			
	Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1); //retirou-se o pagamento do pedido para instanciar depois
	Pedido ped2 = new Pedido(null, sdf.parse("12/11/2017 17:32"), cli1, e2); 

	Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	ped1.setPagamento(pagto1);
	Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("12/12/2017 00:00"), null);
	ped2.setPagamento(pagto2);
	// associar o cliente cli1 aos seus pedidos
	
	cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	
	//criar o PedidoRepository --> dependencia Autowired --> objetos ---> salvar
	
	pedidoRepository.save(Arrays.asList(ped1,ped2));
	pagamentoRepository.save(Arrays.asList(pagto1,pagto2));
	
	
	//instanciar Pedidos e Itens de Pedido
	
	ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
	ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
	ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	
	//Associar cada pedido com o itens - cada pedido deve conhecer os seus itens
	ped1.getItens().addAll(Arrays.asList(ip1,ip2));
	ped2.getItens().addAll(Arrays.asList(ip3));
	
	// cada produto conhecer os seus itens
	p1.getItens().addAll(Arrays.asList(ip1));
	p2.getItens().addAll(Arrays.asList(ip3));
	p3.getItens().addAll(Arrays.asList(ip2));
	
	//Salvar na base de dados
		/*Criar um repository
		 * comando de salvar
		 * 	Dependencia: @Autowired
		 * 	criar a variável: private ItemPedidoRepository itemPedidoRepository;
		 * 	salvar no banco: 
		 * 
		 */
	itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
		
	}
		
}

