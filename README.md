# 🎮 Sistema de Quiz Multi-usuário em Java

Projeto acadêmico desenvolvido em Java, focado na aplicação de conceitos de Programação Orientada a Objetos (POO), manipulação de coleções (Collections) e validação de dados de entrada. O sistema consiste em um jogo de perguntas e respostas com divisão de papéis entre Administradores e Jogadores.

## 🚀 Funcionalidades

O sistema possui dois perfis principais de interação:

**Perfil Administrador (Criador de Conteúdo):**
* Criação de salas de jogo com código único (PIN) e limite de jogadores.
* Cadastro dinâmico de perguntas de Múltipla Escolha (A, B, C, D) e Verdadeiro ou Falso (V/F).
* Configuração do nível de dificuldade das questões (Fácil, Médio, Difícil).
* Visualização do Ranking final das salas.

**Perfil Jogador (Participante):**
* Acesso seguro às salas utilizando o código gerado pelo administrador.
* Sistema de bloqueio automático para salas cheias ou jogadores que já participaram da partida.
* Resolução do quiz com sistema de pontuação.

**Core do Sistema:**
* Sistema de Ranking ordenado automaticamente (do maior para o menor pontuador).
* Validação robusta de entrada de dados (proteção contra "InputMismatchException" e falhas de buffer de teclado).
* Interface via terminal (Console) com navegação fluida.

## 🛠️ Tecnologias e Estruturas Utilizadas

* **Linguagem:** Java
* **IDE Recomendada:** Apache NetBeans (ou qualquer IDE com suporte a projetos Java)
* **Estruturas de Dados:** `ArrayList` (para listagem de jogos e perguntas) e `HashMap` (para associação de usuários e pontuações no ranking).
* **Versionamento:** Git e GitHub.

## ⚙️ Como executar o projeto

1. Faça o clone deste repositório na sua máquina local:
   ```bash
   git clone https://github.com/VitorVieira28/Quiz-Poo
