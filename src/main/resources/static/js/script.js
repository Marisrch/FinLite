document.addEventListener("DOMContentLoaded", function () {

    const material = document.getElementById("idMaterial");
    const quantidade = document.getElementById("quantidadeLancamento");
    const custoUnitario = document.getElementById("custoUnitarioLancamento");
    const valorTotal = document.getElementById("valorTotalLancamento");
    const mensagem = document.getElementById("mensagemValorTotal");

    if (!material || !quantidade || !custoUnitario || !valorTotal) {
        return;
    }

    function atualizarValorTotal() {

        const compraMaterial = material.value !== "";

        if (compraMaterial) {

            valorTotal.readOnly = true;
            valorTotal.placeholder = "Calculado automaticamente";

            const qtd = Number(quantidade.value);
            const custo = Number(custoUnitario.value);

            if (quantidade.value === "" || custoUnitario.value === "" ||
                qtd <= 0 || custo < 0) {
                valorTotal.value = "";
                if (mensagem) {
                    mensagem.textContent =
                        "Informe a quantidade e o custo unitário para calcular o total.";
                }
                return;
            }

            valorTotal.value = (qtd * custo).toFixed(2);

            if (mensagem) {
                mensagem.textContent = "Valor calculado automaticamente.";
            }

        } else {

            valorTotal.readOnly = false;
            valorTotal.placeholder = "Ex.: 50.00";

            if (mensagem) {
                mensagem.textContent =
                    "Informe manualmente o valor total da despesa.";
            }
        }
    }

    material.addEventListener("change", atualizarValorTotal);
    quantidade.addEventListener("input", atualizarValorTotal);
    custoUnitario.addEventListener("input", atualizarValorTotal);

    atualizarValorTotal();
})


document.addEventListener("DOMContentLoaded", function () {

    const cpf = document.getElementById("cpfEmpreendedor");

    if (!cpf) {
        return;
    }

    function formatarCPF(valor) {

        let numeros = valor.replace(/\D/g, "").slice(0, 11);

        numeros = numeros.replace(
            /(\d{3})(\d)/,
            "$1.$2"
        );

        numeros = numeros.replace(
            /(\d{3})(\d)/,
            "$1.$2"
        );

        numeros = numeros.replace(
            /(\d{3})(\d{1,2})$/,
            "$1-$2"
        );

        return numeros;
    }

    cpf.addEventListener("input", function () {
        cpf.value = formatarCPF(cpf.value);
    });

    cpf.value = formatarCPF(cpf.value);

});


document.addEventListener("DOMContentLoaded", function () {

    const tabelas = document.querySelectorAll("[data-tabela-filtros]");

    tabelas.forEach(tabela => {

        const cabecalhos = tabela.querySelectorAll("thead th");
        const corpo = tabela.querySelector("tbody");

        const linhas = Array.from(corpo.querySelectorAll("tr"))
            .filter(linha => !linha.classList.contains("linha-sem-registros"));

        let filtrosAtivos = new Map();

        let linhaVazia = corpo.querySelector(".linha-sem-registros");

        const mensagemOriginal = linhaVazia
            ? linhaVazia.cells[0].textContent.trim()
            : "Nenhum registro cadastrado.";

        if (!linhaVazia) {
            linhaVazia = document.createElement("tr");
            linhaVazia.className = "linha-sem-registros";

            const celula = document.createElement("td");
            celula.colSpan = cabecalhos.length;
            celula.textContent = "Nenhum registro cadastrado.";

            linhaVazia.appendChild(celula);
            corpo.appendChild(linhaVazia);
        }

        function atualizarBotaoFiltro(indice, botao) {
            botao.classList.toggle(
                "filtro-ativo",
                filtrosAtivos.has(indice)
            );
        }

        function aplicarFiltros() {

            let quantidadeVisivel = 0;

            linhas.forEach(linha => {

                let corresponde = true;

                filtrosAtivos.forEach((valoresSelecionados, coluna) => {

                    const valorCelula = linha.cells[coluna].textContent.trim();

                    if (!valoresSelecionados.has(valorCelula)) {
                        corresponde = false;
                    }

                });

                linha.style.display = corresponde ? "" : "none";

                if (corresponde) {
                    quantidadeVisivel++;
                }

            });

            linhaVazia.style.display =
                quantidadeVisivel === 0 ? "" : "none";

            if (quantidadeVisivel === 0)

                linhaVazia.cells[0].textContent =
                    linhas.length === 0
                        ? mensagemOriginal
                        : "Nenhum registro encontrado com esses filtros.";

            }

        

        cabecalhos.forEach((cabecalho, indice) => {

            if (cabecalho.hasAttribute("data-coluna-acoes")) {
                return;
            }

            const botao = cabecalho.querySelector(".btn-filtro");

            if (!botao) {
                return;
            }

            botao.addEventListener("click", function (evento) {

                evento.stopPropagation();

                document.querySelectorAll(".menu-filtro")
                    .forEach(menu => menu.remove());

                const valores = [
                    ...new Set(
                        linhas.map(linha =>
                            linha.cells[indice].textContent.trim()
                        )
                    )
                ].sort((a, b) =>
                    a.localeCompare(b, "pt-BR", {
                        numeric: true,
                        sensitivity: "base"
                    })
                );

                const menu = document.createElement("div");
                menu.className = "menu-filtro";

                const pesquisa = document.createElement("input");
                pesquisa.type = "text";
                pesquisa.placeholder = "Pesquisar...";
                pesquisa.className = "pesquisa-filtro";

                const selecionarTodos = document.createElement("label");
                selecionarTodos.className = "opcao-filtro";

                const checkboxTodos = document.createElement("input");
                checkboxTodos.type = "checkbox";

                selecionarTodos.appendChild(checkboxTodos);
                selecionarTodos.appendChild(
                    document.createTextNode("Selecionar todos")
                );

                const lista = document.createElement("div");
                lista.className = "lista-filtro";

                const selecao = filtrosAtivos.has(indice)
                    ? new Set(filtrosAtivos.get(indice))
                    : new Set(valores);

                const opcoes = [];

                function atualizarOpcoes() {

                    const termo = pesquisa.value
                        .trim()
                        .toLocaleLowerCase("pt-BR");

                    opcoes.forEach(opcao => {

                        opcao.elemento.style.display =
                            opcao.valor.toLocaleLowerCase("pt-BR")
                                .includes(termo) ? "" : "none";

                        opcao.checkbox.checked =
                            selecao.has(opcao.valor);

                    });

                    const visiveis = opcoes.filter(opcao =>
                        opcao.elemento.style.display !== "none"
                    );

                    checkboxTodos.checked =
                        visiveis.length > 0 &&
                        visiveis.every(opcao =>
                            selecao.has(opcao.valor)
                        );

                    checkboxTodos.indeterminate =
                        visiveis.some(opcao =>
                            selecao.has(opcao.valor)
                        ) && !checkboxTodos.checked;

                }

                valores.forEach(valor => {

                    const elemento = document.createElement("label");
                    elemento.className = "opcao-filtro";

                    const checkbox = document.createElement("input");
                    checkbox.type = "checkbox";
                    checkbox.checked = selecao.has(valor);

                    checkbox.addEventListener("change", function () {

                        if (checkbox.checked) {
                            selecao.add(valor);
                        } else {
                            selecao.delete(valor);
                        }

                        atualizarOpcoes();

                    });

                    elemento.appendChild(checkbox);
                    elemento.appendChild(
                        document.createTextNode(valor || "(Vazio)")
                    );

                    lista.appendChild(elemento);

                    opcoes.push({
                        valor,
                        elemento,
                        checkbox
                    });

                });

                checkboxTodos.addEventListener("change", function () {

                    const visiveis = opcoes.filter(opcao =>
                        opcao.elemento.style.display !== "none"
                    );

                    visiveis.forEach(opcao => {

                        if (checkboxTodos.checked) {
                            selecao.add(opcao.valor);
                        } else {
                            selecao.delete(opcao.valor);
                        }

                    });

                    atualizarOpcoes();

                });

                pesquisa.addEventListener("input", atualizarOpcoes);

                const botoes = document.createElement("div");
                botoes.className = "acoes-filtro";

                const limpar = document.createElement("button");
                limpar.type = "button";
                limpar.textContent = "Limpar";

                limpar.addEventListener("click", function () {

                    filtrosAtivos.delete(indice);

                    atualizarBotaoFiltro(indice, botao);

                    menu.remove();

                    aplicarFiltros();

                });

                const aplicar = document.createElement("button");
                aplicar.type = "button";
                aplicar.textContent = "Aplicar";

                aplicar.addEventListener("click", function () {

                    if (selecao.size === valores.length) {
                        filtrosAtivos.delete(indice);
                    } else {
                        filtrosAtivos.set(indice, new Set(selecao));
                    }

                    atualizarBotaoFiltro(indice, botao);

                    menu.remove();

                    aplicarFiltros();

                });

                botoes.appendChild(limpar);
                botoes.appendChild(aplicar);

                menu.appendChild(pesquisa);
                menu.appendChild(selecionarTodos);
                menu.appendChild(lista);
                menu.appendChild(botoes);

                cabecalho.appendChild(menu);

                atualizarOpcoes();

                pesquisa.focus();

            });

        });

        aplicarFiltros();

    });

});
