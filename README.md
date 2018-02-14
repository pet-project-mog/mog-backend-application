# Mog Backend Application Layer

A camada de aplicação é responsável por expor serviços que serão consumidos pela camada de **UI**.

Por ser uma aplicação _web_ ela é responsável por manipular qualquer _requests_ vindo da camada de **UI** e dispachar 
para a camada de **Domain** quando houver necessidade e executar alguma regra de negócio.

**OBS:** Essa camada só deve conter lógicas para lidar com _request_ e _response_. Qualquer outro tipo de lógica deve ser 
efetuada na camada de **Domain**.