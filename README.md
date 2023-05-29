# Android-Checklist

Descrição
A aplicação de checklist de ônibus apresenta um formulário que contém vários itens que precisam ser verificados antes do ônibus poder partir. É como a checklist de aviação que os pilotos verificam antes do voo decolar. O conteúdo do formulário será obtido de uma API e pode ter diferentes tipos de entradas, dependendo do tipo de resposta necessário.

# Objetivos

Poder usar -  /checklists/anonymous sem autenticação.
Obter o conteúdo do formulário do servidor, no formato JSON.
Apresentar o checklist ao usuário.
Verificar se as verificações necessárias foram preenchidas.
Verificar se a resposta corresponde ao formato, apenas para números.
Enviar o resultado para a API.

# BÔNUS

Usar - /checklists/authorized com o token JWT obtido de /auth.
Criar um formulário de login para acessar o formulário do checklist e salvar o token obtido para uso posterior.
Fazer logout e excluir o token quando sair do checklist ou quando o token expirar.

Nome de usuário: 1111
Senha: 1965


![image](https://github.com/gabriellbernardes/Android-Checklist/assets/48017492/ae592810-5a05-4297-8da4-62a7d9201741)

![image](https://github.com/gabriellbernardes/Android-Checklist/assets/48017492/8d2e0c2f-1fac-467f-8c88-8d8ce08331ab)

![image](https://github.com/gabriellbernardes/Android-Checklist/assets/48017492/b98f8104-a9c1-4878-b769-a5f547656f36)



Checklist for test -

shortlist: https://xi-checklists.azurewebsites.net/Checklists/anonymous/shortlist
Contains one example of each type to test.

checklist: https://xi-checklists.azurewebsites.net/Checklists/anonymous/checklist
Contains a real world example of a bus checklist.

Endpoints -

Swagger: https://xi-checklists.azurewebsites.net/swagger/ui
